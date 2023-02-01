package com.dashboard.api.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;

import com.dashboard.api.model.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;

//import lombok.Data;

public class CusUsersDetails implements UserDetails {

    private static final long serialVersionUID=1L;

    private Long id;
    
    public Long getId() {
        return id;
    }

    private String name;

    public String getName() {
        return name;
    }

    private String username;

    private String email;
    public String getEmail() {
        return email;
    }

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public CusUsersDetails(
        Long id, 
        String name, 
        String username, 
        String email, 
        String password,
        Collection<? extends GrantedAuthority> authorities
        ) 
        {
        this.id = id;
        this.name=name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserDetails build(Users users) {

		return new CusUsersDetails(
				users.getId(), 
				users.getUsername(), 
				users.getEmail(),
				users.getPassword()
				);
	}

    public CusUsersDetails(long id, String username, String email, String password) {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }    

    @Override
    public boolean equals(Object o) {
        if (this==o) {
            return true;
        }

        if (o==null || getClass()!= o.getClass()) {
            return false;
        }

        CusUsersDetails user =(CusUsersDetails) o;
        return Objects.equals(id, user.id);
    }
    
}
