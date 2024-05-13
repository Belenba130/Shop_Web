package com.example.shop_web.service;



import com.example.shop_web.model.dto.request.SignUpRequest;
import com.example.shop_web.model.dto.request.UserUpdateRequest;
import com.example.shop_web.model.dto.response.TokenResponse;
import com.example.shop_web.model.dto.response.UserResponse;
import com.example.shop_web.model.entity.UsersEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    UsersEntity addUser(SignUpRequest request,MultipartFile file);
    UserResponse showUser();
    UserResponse update(UserUpdateRequest request, MultipartFile file);
    void addRole(Long id, Long roleId);
    void deleteRole(long userId, long roleId);
}
