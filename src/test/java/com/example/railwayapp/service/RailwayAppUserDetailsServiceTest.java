package com.example.railwayapp.service;

import com.example.railwayapp.model.entity.Enum.UserRole;
import com.example.railwayapp.model.entity.User;
import com.example.railwayapp.repository.UserRepository;
import com.example.railwayapp.service.impl.RailwayAppUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class RailwayAppUserDetailsServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RailwayAppUserDetailsService userDetailsService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a user instance for testing
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setUsername("testUser");
        user.setRole(UserRole.USER);  // Set role for testing
    }

    @Test
    void testLoadUserByUsername_UserFound() {
        // Mock the repository to return a user when searching by email
        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(user));

        // Execute the method
        UserDetails userDetails = userDetailsService.loadUserByUsername("test@example.com");

        // Verify the returned UserDetails
        assertNotNull(userDetails);
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_USER")));
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Mock the repository to return empty when searching by email
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        // Execute the method and verify exception
        assertThrows(UsernameNotFoundException.class, () ->
                userDetailsService.loadUserByUsername("nonexistent@example.com")
        );

        // Verify the error message
        try {
            userDetailsService.loadUserByUsername("nonexistent@example.com");
        } catch (UsernameNotFoundException e) {
            assertEquals("User with email nonexistent@example.com not found!", e.getMessage());
        }
    }
}
