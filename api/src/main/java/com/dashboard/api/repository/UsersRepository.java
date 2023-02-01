package com.dashboard.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dashboard.api.model.Users;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long>{

    Optional<Users> findByUsername(String username);

    Boolean existsByUsernameAndPassword(String username,String password);  
    
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    

    // 
}
