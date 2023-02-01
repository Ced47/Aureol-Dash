package com.dashboard.api.controller;
    
//import java.util.stream.Collector;
//import java.util.stream.Collectors;
//import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.dashboard.api.security.jwt.JwtUtils;
//import com.dashboard.api.security.services.CusUsersDetails;
import com.dashboard.api.security.services.UsersService;
import com.dashboard.api.Payload.*;
import com.dashboard.api.model.Users;
import com.dashboard.api.repository.UsersRepository;


@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
public class UsersController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private UsersService usersService;

    // register 
    @PostMapping("/users/signin")
    public ResponseEntity<?> authUser(@Valid @RequestBody LoginRequest loginRequest) {
        if (usersRepository.existsByUsernameAndPassword(loginRequest.getUsername(),loginRequest.getPassword())) {
            return ResponseEntity.ok("User sign in successfully");
        }
        else {
            return ResponseEntity.badRequest().body(new LoginResponse("Username or password is invalid"));
        }
        /*Authentication authentication= authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        CusUsersDetails userDetails=(CusUsersDetails) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles= userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());

        return ResponseEntity
               .ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
               .body(new LoginUserResponse(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(), roles
               ));*/
    }

    // create a new user
    @PostMapping("/users")
    public ResponseEntity<?> createUsers(@Valid @RequestBody CreateRequestUser createRequestUser) {
        // check if user exist
        if (usersRepository.existsByUsername(createRequestUser.getUsername())) {
            return ResponseEntity.badRequest().body(new LoginResponse("Error: Username is already taken!"));
        } 
        if (usersRepository.existsByEmail(createRequestUser.getEmail())) {
            return ResponseEntity.badRequest().body(new LoginResponse("Error: Email is already in user!"));
        }

        // create user 
        Users users = new Users(createRequestUser.getName(),createRequestUser.getUsername(),createRequestUser.getEmail(),createRequestUser.getPassword());
       
        usersService.saveUsers(users);
        
        return ResponseEntity.ok("User registered successfully");
    }

    // logout of user
    @PostMapping("/users/logout")
    public ResponseEntity<?> logOut() {
        ResponseCookie cookie=jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body("You have been signed out");
    }

    // get one user
    @GetMapping("/users/{id}")
    public Users getUsers(@PathVariable("id") final Long id) {
        Optional<Users> user=usersService.getUsers(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            return null;
        }
    }

    // get all users 
    @GetMapping("/users")
    public Iterable<Users> getUsers() {
        return usersService.getUsers();
    }
    
    // update an user
    @PutMapping("/users/{id}")
    public Users updateUser(@PathVariable("id") Long id,@RequestBody Users user) {
        Users u= usersService.updateUsers(id,user);
        return u;
    }

    // delete an user
    @DeleteMapping("/users/{id}")
    public void deleteUsers(@PathVariable("id") final Long id) {
        usersService.deleteUsers(id);
    }


}
