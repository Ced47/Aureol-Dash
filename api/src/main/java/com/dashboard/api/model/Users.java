package com.dashboard.api.model;

import javax.persistence.Entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Entity
@Table(name="users", uniqueConstraints = 
{
    @UniqueConstraint(columnNames = "name"),
    @UniqueConstraint(columnNames = "email")
})

public class Users {
    // attributes 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    @Size(max = 60)
    @NotBlank
    private String name;

    @Column(name="username")
    @Size(max = 60)
    @NotBlank
    private String username;

    @Email
    @Size(max = 100)
    @NotBlank
    private String email;

    @Size(max = 200)
    @NotBlank
    private String password;

    // default constructor
    public Users() {
    }

    // constructor with parameters
    
    public Users(@Size(max = 60) @NotBlank String name, @Size(max = 60) @NotBlank String username,
            @Email @Size(max = 100) @NotBlank String email, @Size(max = 200) @NotBlank String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }

}
