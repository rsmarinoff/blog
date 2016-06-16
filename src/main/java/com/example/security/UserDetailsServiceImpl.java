/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author dilyan
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = repository.findOneByUsername(string);
        if(user == null){
            throw new UsernameNotFoundException("User with name " + string + " not found.");
        }
        return user;
    }
    
    public User addUser(User user){
        if(repository.findOneByUsername(user.getUsername()) != null){
            return new User();
        }
        return repository.save(user);
    }
    
    public User deleteUser(long id){
        User user = repository.findOne(id);
        if(user == null){
            return new User();
        }
        repository.delete(user);
        return user;
    }
    
    public User updateUser(User user){
        User localUser = repository.findOne(user.getId());
        if(localUser == null){
            return new User();
        }
        return repository.save(user);
    }
    
    public User getUserById(long id){
        User localUser = repository.findOne(id);
        if(localUser == null){
            return new User();
        }
        return repository.findOne(id);
    }
    
    public List<User> listAll(){
        return repository.findAll();
    }
    
    public User deleteUserById(long id){
        User localUser = repository.findOne(id);
        if(localUser == null){
            return new User();
        }
        repository.delete(id);
        return localUser;
    }
    
    @PostConstruct
    public void addDefaultUsers(){
        repository.save(new User("admin", "admin", "ROLE_ADMIN", "ROLE_BLOGGER"));
        repository.save(new User("user", "user", "ROLE_USER"));
    }
    
}
