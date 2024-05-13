package com.example.shop_web.controller;

import com.example.shop_web.exception.BaseException;
import com.example.shop_web.model.dto.request.SignInRequest;
import com.example.shop_web.model.dto.request.SignUpRequest;
import com.example.shop_web.model.dto.request.UserDetailsAdapter;
import com.example.shop_web.model.dto.response.SignInResponse;
import com.example.shop_web.model.dto.response.SignUpResponse;
import org.springframework.security.core.GrantedAuthority;
import com.example.shop_web.model.entity.UsersEntity;
import com.example.shop_web.service.imp.UserDetailService;
import com.example.shop_web.validator.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailService userDetailsService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> register(@RequestBody SignUpRequest request,
                                                   @RequestParam(value = "file", required = false) MultipartFile file) {
        UsersEntity user = userDetailsService.addUser(request, file);
        SignUpResponse response = new SignUpResponse("Register successful!");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity getToken(@RequestBody SignInRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            UserDetailsAdapter userDetailsAdapter = (UserDetailsAdapter) userDetails;
            String token = jwtUtil.generateToken(userDetails);

            SignInResponse signInResponse = new SignInResponse();
            signInResponse.setUserId(userDetailsAdapter.getUserId());
            signInResponse.setUserName(userDetailsAdapter.getUsername());
            signInResponse.setToken(token);

            List<String> roles = userDetailsAdapter.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());
            signInResponse.setRoles(roles);
            return new ResponseEntity(signInResponse, HttpStatus.OK);
        } catch (AuthenticationException a){
            throw  new BaseException("RA-00-401");
        }
    }

}
