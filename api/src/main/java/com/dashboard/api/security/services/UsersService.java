package com.dashboard.api.security.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dashboard.api.repository.UsersRepository;
import com.dashboard.api.model.Users;

import lombok.Data;

@Data 
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    // récupérer un user 
    public Optional <Users> getUsers(final Long id) {
        return usersRepository.findById(id);
    }

    // récupérer tous les users 
    public Iterable<Users> getUsers() {
        return usersRepository.findAll();
    }

    // update a user
    public Users updateUsers(Long id,Users user) {
        Users u=usersRepository.findById(id).get();
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());

        usersRepository.save(u);
        return u;
    }

    // delete a user
    public void deleteUsers(final Long id) {
        usersRepository.deleteById(id);
    }

    // save a user
    public Users saveUsers(Users users) {
        Users saveUsers=usersRepository.save(users);
        return saveUsers;
    }


}
