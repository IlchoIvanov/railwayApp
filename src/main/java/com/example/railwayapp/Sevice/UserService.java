package com.example.railwayapp.Sevice;

import com.example.railwayapp.Model.Dto.UserInfoDto;
import com.example.railwayapp.Model.Dto.UserRegisterDto;
import com.example.railwayapp.Model.Entity.User;

public interface UserService {
    void  registerUser(UserRegisterDto data);

    User findUserByEmail(String email);
    User findUserByUsername(String username);

    UserInfoDto findUserInfoByEmail(String username);
}
