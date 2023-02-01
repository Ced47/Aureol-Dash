package com.dashboard.api.Payload;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateRequestUser {

    @NotBlank
    @Size(min=3, max = 30)
    private String name;

    @NotBlank
    @Size(min=3, max = 30)
    private String username;

    @NotBlank
    @Size(min=3, max = 60)
    @Email
    private String email;

    
    @NotBlank
    @Size(min=6, max = 50)
    private String password;
    
    private Set<String> role;
}
