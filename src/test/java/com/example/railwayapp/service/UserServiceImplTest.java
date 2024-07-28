package com.example.railwayapp.service;

import com.example.railwayapp.Model.Dto.UserInfoDto;
import com.example.railwayapp.Model.Dto.UserRegisterDto;
import com.example.railwayapp.Model.Entity.Station;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Repository.UserRepository;
import com.example.railwayapp.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


        @Mock
        private PasswordEncoder passwordEncoder;

        @Mock
        private UserRepository userRepository;

        @Mock
        private ModelMapper modelMapper;

        @InjectMocks
        private UserServiceImpl userService;

        private User user;
        private UserRegisterDto userRegisterDto;
        private UserInfoDto userInfoDto;
        private Station station;

        @BeforeEach
        void setUp() {
            user = new User();
            user.setId(1L);
            user.setEmail("test@example.com");
            user.setUsername("testuser");
            user.setPassword("password");
            user.setUserPictures(Arrays.asList());
            user.setVisitedStations(Arrays.asList());

            userRegisterDto = new UserRegisterDto();
            userRegisterDto.setEmail("test@example.com");
            userRegisterDto.setUsername("testuser");
            userRegisterDto.setPassword("password");

            userInfoDto = new UserInfoDto();
            userInfoDto.setEmail("test@example.com");
            userInfoDto.setUsername("testuser");
            userInfoDto.setUploadedPictures(0);
            userInfoDto.setVisitedStations(Arrays.asList());

            station = new Station();
            station.setId(1L);
            station.setName("Test Station");
        }

        @Test
        void registerUser() {
            when(modelMapper.map(userRegisterDto, User.class)).thenReturn(user);
            when(passwordEncoder.encode(userRegisterDto.getPassword())).thenReturn("encodedPassword");

            userService.registerUser(userRegisterDto);

            verify(userRepository, times(1)).save(user);
            assertEquals("encodedPassword", user.getPassword());
        }

        @Test
        void findUserByEmail() {
            when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

            User foundUser = userService.findUserByEmail("test@example.com");

            assertNotNull(foundUser);
            assertEquals(user, foundUser);
            verify(userRepository, times(1)).findByEmail("test@example.com");
        }

        @Test
        void findUserByUsername() {
            when(userRepository.findByUsername("testuser")).thenReturn(user);

            User foundUser = userService.findUserByUsername("testuser");

            assertNotNull(foundUser);
            assertEquals(user, foundUser);
            verify(userRepository, times(1)).findByUsername("testuser");
        }

        @Test
        void findUserInfoByEmail() {
            user.setVisitedStations(Arrays.asList(station));
            when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));
            when(modelMapper.map(user, UserInfoDto.class)).thenReturn(userInfoDto);

            UserInfoDto foundUserInfoDto = userService.findUserInfoByEmail("test@example.com");

            assertNotNull(foundUserInfoDto);
            assertEquals(userInfoDto, foundUserInfoDto);
            assertEquals(1, foundUserInfoDto.getVisitedStations().size());
            assertEquals("Test Station", foundUserInfoDto.getVisitedStations().get(0));
            verify(userRepository, times(1)).findByEmail("test@example.com");
        }

        @Test
        void getAllUsers() {
            user.setVisitedStations(Arrays.asList(station));
            when(userRepository.findAll()).thenReturn(Arrays.asList(user));
            when(modelMapper.map(user, UserInfoDto.class)).thenReturn(userInfoDto);

            List<UserInfoDto> allUsers = userService.getAllUsers();

            assertNotNull(allUsers);
            assertEquals(1, allUsers.size());
            assertEquals(userInfoDto, allUsers.get(0));
            verify(userRepository, times(1)).findAll();
        }
    }

