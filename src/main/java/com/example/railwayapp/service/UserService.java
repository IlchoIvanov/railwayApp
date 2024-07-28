package com.example.railwayapp.service;

import com.example.railwayapp.model.dto.UserInfoDto;
import com.example.railwayapp.model.dto.UserRegisterDto;
import com.example.railwayapp.model.entity.User;

import java.util.List;

public interface UserService {
    void  registerUser(UserRegisterDto data);

    User findUserByEmail(String email);
    User findUserByUsername(String username);

    UserInfoDto findUserInfoByEmail(String username);
    List<UserInfoDto> getAllUsers();
}
