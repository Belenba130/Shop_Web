package com.example.shop_web.service.imp;

import com.example.shop_web.exception.BaseException;
import com.example.shop_web.model.dto.request.SignUpRequest;
import com.example.shop_web.model.dto.request.UserDetailsAdapter;
import com.example.shop_web.model.dto.request.UserUpdateRequest;
import com.example.shop_web.model.dto.response.RoleResponse;
import com.example.shop_web.model.dto.response.UserResponse;
import com.example.shop_web.model.entity.RoleEntity;
import com.example.shop_web.model.entity.UserRoleEntity;
import com.example.shop_web.model.entity.UsersEntity;
import com.example.shop_web.repository.RoleRepository;
import com.example.shop_web.repository.UserRepository;
import com.example.shop_web.repository.UserRoleRepository;
import com.example.shop_web.service.ShoppingService;
import com.example.shop_web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class UserDetailService implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ShoppingService shoppingService;
    @Autowired
    IStorageService iStorageService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = userRepository.findUsersEntityByUsername(username);
        if(usersEntity!=null){
            UserDetails userDetails = new UserDetailsAdapter(usersEntity);
            return userDetails;
        }
        throw new UsernameNotFoundException("Username \"" + username + "\" not found!");
    }

    public UsersEntity findByUsername(String username) {
        UsersEntity usersEntity = userRepository.findUsersEntityByUsername(username);
        if(usersEntity!=null){
            return usersEntity;
        }
        throw new UsernameNotFoundException("Username \"" + username + "\" not found!");
    }

    public UsersEntity addUser(SignUpRequest userRequest, MultipartFile file) {
        List<RoleEntity> roles = new ArrayList<>();
        for (String roleName : userRequest.getRoles()) {
            RoleEntity role = roleRepository.findByRoleName(roleName);
            if (role != null) {
                roles.add(role);
            } else {
                throw new BaseException("RA-01-400");
            }
        }
        String encodedPassword = passwordEncoder.encode(userRequest.getPassword());

        UsersEntity user = new UsersEntity();
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setFullname(userRequest.getFullname());
        user.setStatus(true);
        user.setPassword(encodedPassword);
        user.setPhone(userRequest.getPhone());
        user.setAddress(userRequest.getAddress());
        user.setCreatedTime(new Timestamp(System.currentTimeMillis()));
        String avatarUrl = null;
        if (file != null && !file.isEmpty()) {
            try {
                avatarUrl = iStorageService.uploadFile(file);
                user.setAvatar(avatarUrl);
            } catch (IOException e) {
                e.printStackTrace();
                throw new BaseException("Error storing avatar file: " + e.getMessage());
            }
        }

        UsersEntity savedUser = userRepository.save(user);

        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        for (RoleEntity role : roles) {
            UserRoleEntity newUserRole = new UserRoleEntity();
            newUserRole.setUserId(savedUser.getUserId());
            newUserRole.setRoleId(role.getRoleId());
            newUserRole.setRolesByRoleId(role);
            newUserRole.setUsersByUserId(savedUser);

            userRoleEntities.add(newUserRole);
            userRoleRepository.save(newUserRole);
        }
        savedUser.setUserRoleEntities(userRoleEntities);
        return savedUser;
    }



    @Override
    public UserResponse showUser() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        Optional<UsersEntity> userOptional = Optional.ofNullable(userRepository.findUsersEntityByUsername(name));
        if (userOptional.isPresent()) {
            return convertToResponse(userOptional.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    @Override
    public UserResponse update(UserUpdateRequest request, MultipartFile file) {
        UsersEntity user = shoppingService.userUsing();
        user.setFullname(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        if (file != null && !file.isEmpty()) {
            try {
                String avatarUrl = iStorageService.uploadFile(file);
                user.setAvatar(avatarUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
        return convertToResponse(user);
    }

    @Override
    public void addRole(Long id, Long roleId) {
        UsersEntity user = userRepository.findById(id).orElse(null);
        RoleEntity role = roleRepository.findById(roleId).orElse(null);
        UserRoleEntity userRole = userRoleRepository.findByUserIdAndRoleId(id,roleId);
        if (userRole != null) {
            throw new BaseException("User already has this role.");
        }
        UserRoleEntity newUserRole = new UserRoleEntity();
        newUserRole.setUsersByUserId(user);
        newUserRole.setRolesByRoleId(role);
        userRoleRepository.save(newUserRole);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(long userId, long roleId) {
        UsersEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException("User not found with ID: " + userId));

        RoleEntity role = roleRepository.findById(roleId)
                .orElseThrow(() -> new BaseException("Role not found with ID: " + roleId));

        UserRoleEntity userRole = userRoleRepository.findByUserIdAndRoleId(userId, roleId);
        if (userRole != null) {
            user.getUserRoleEntities().remove(userRole);
            userRoleRepository.delete(userRole);
            userRepository.save(user);
        } else {
            throw new BaseException("User does not have this role.");
        }
    }


    public UserResponse convertToResponse(UsersEntity usersEntity){
        UserResponse response = new UserResponse();
        response.setUsername(usersEntity.getUsername());
        response.setFullName(usersEntity.getFullname());
        response.setEmail(usersEntity.getEmail());
        response.setPhone(usersEntity.getPhone());
        response.setAddress(usersEntity.getAddress());
        response.setAvatarUrl(usersEntity.getAvatar());
        return response;
    }
    public Page<UsersEntity> getUser(Pageable pageable){
        return userRepository.findAll(pageable);
    }
    public UsersEntity findByName(String name){
        return userRepository.findUsersEntityByUsername(name);
    }

    public void UserStatus(long userId) {
        UsersEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new BaseException("RA-39-401"));
        user.setStatus(!user.getStatus());

        userRepository.save(user);
    }

    public List<RoleResponse> getAllUserRoles() {
        List<UserRoleEntity> userRoles = userRoleRepository.findAll();
        Map<Long, List<String>> userRolesMap = new HashMap<>();
        for (UserRoleEntity userRole : userRoles) {
            long userId = userRole.getUserId();
            List<String> roles = userRolesMap.computeIfAbsent(userId, k -> new ArrayList<>());
            roles.add(userRole.getRolesByRoleId().getRoleName());
        }
        List<RoleResponse> roleResponses = new ArrayList<>();
        for (Map.Entry<Long, List<String>> entry : userRolesMap.entrySet()) {
            RoleResponse roleResponse = new RoleResponse();
            roleResponse.setId(entry.getKey());
            roleResponse.setRoleName(entry.getValue());
            roleResponses.add(roleResponse);
        }
        return roleResponses;
    }

}
