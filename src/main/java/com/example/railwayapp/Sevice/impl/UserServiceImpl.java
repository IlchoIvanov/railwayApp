package com.example.railwayapp.Sevice.impl;

import com.example.railwayapp.Model.Dto.UserInfoDto;
import com.example.railwayapp.Model.Dto.UserRegisterDto;
import com.example.railwayapp.Model.Entity.Station;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Repository.UserRepository;
import com.example.railwayapp.Sevice.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto data) {
        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));
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
        UserInfoDto userInfoDto = modelMapper.map(user, UserInfoDto.class);
        userInfoDto.setUploadedPictures(user.getUserPictures().size());
        userInfoDto.setVisitedStations(user.getVisitedStations().stream().map(Station::getName).collect(Collectors.toCollection(ArrayList::new)));
        return userInfoDto;
    }

    @Override
    public List<UserInfoDto> getAllUsers() {
        List<User> all = userRepository.findAll();
        List<UserInfoDto> userInfoDtos = new ArrayList<>();
        for (User user : all) {
            UserInfoDto userInfoDto = modelMapper.map(user, UserInfoDto.class);
            userInfoDto.setUploadedPictures(user.getUserPictures().size());
            userInfoDto.setVisitedStations(user.getVisitedStations().stream().map(Station::getName).collect(Collectors.toList()));
            userInfoDtos.add(userInfoDto);
        }
        return userInfoDtos;
    }
}
