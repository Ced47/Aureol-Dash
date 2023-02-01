package com.dashboard.api.Payload;

import java.util.List;

import lombok.Data;

//import antlr.collections.List;

@Data
public class LoginUserResponse {
    
    public LoginUserResponse(Long id2, String username2, String email2, List<String> roles) {
    }

    private Long id;

    private String name;

    private String username;

    private String email;

    private List<String> role;
}
