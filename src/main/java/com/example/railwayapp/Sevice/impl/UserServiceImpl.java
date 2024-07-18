package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Dto.UserInfoDto;
import com.example.railwayapp.Model.Dto.UserRegisterDto;
import com.example.railwayapp.Model.Entity.Station;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Repository.UserRepository;
import com.example.railwayapp.Sevice.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegisterDto data) {
        User user = new User();
        user.setUsername(data.getUsername());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEmail(data.getEmail());
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserInfoDto findUserInfoByEmail(String username) {
        User user = findUserByEmail(username);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setId(user.getId());
        userInfoDto.setLevel(user.getLevel());
        userInfoDto.setRole(user.getRole());
        userInfoDto.setUploadedPictures(user.getUserPictures().size());
        userInfoDto.setVisitedStations(user.getVisitedStations().stream().map(Station::getName).collect(Collectors.toCollection(ArrayList::new)));
        //todo: mapper
        return userInfoDto;
    }
}
