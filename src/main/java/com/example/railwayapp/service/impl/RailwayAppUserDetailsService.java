package com.example.railwayapp.service.impl;

import com.example.railwayapp.Model.Entity.Enum.UserRole;
import com.example.railwayapp.Model.Entity.User;
import com.example.railwayapp.Model.User.RailwayAppUserDetails;
import com.example.railwayapp.Repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class RailwayAppUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public RailwayAppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository
                .findByEmail(email)
                .map(RailwayAppUserDetailsService::map)
                .orElseThrow(
                        () -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }
    private static UserDetails map(User userEntity) {

        return new RailwayAppUserDetails(
                userEntity.getEmail(),
                userEntity.getPassword(),
                List.of(map(userEntity.getRole())), userEntity.getUsername()
        );
    }

    private static GrantedAuthority map(UserRole role) {
        return new SimpleGrantedAuthority(
                "ROLE_" + role
        );
    }
}
