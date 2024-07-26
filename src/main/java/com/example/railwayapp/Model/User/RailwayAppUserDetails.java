package com.example.railwayapp.Model.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
public class RailwayAppUserDetails extends User {
    private final String name;
    public RailwayAppUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String name) {
        super(username, password, authorities);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
