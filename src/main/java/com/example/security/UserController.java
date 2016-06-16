/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dilyan
 */
@RestController
@RequestMapping("/")
public class UserController {
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @RequestMapping(value="/user", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody User addUser(@RequestBody UserDTO user){
        return userDetailsService.addUser(new User(user.getUsername(), user.getPassword(), user.getRoles()));
    }
    
    @RequestMapping(value="/user", method=RequestMethod.PUT, consumes="application/json")
    public @ResponseBody User updateUser(@RequestParam long id, @RequestBody UserDTO user){
        return userDetailsService.updateUser(new User(id, user.getUsername(), user.getPassword(), user.getRoles()));
    }
    
    @RequestMapping(value="/users", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<User> listAllUsers(){
        return userDetailsService.listAll();
    }
    
    @RequestMapping(value="/user/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody User getUserById(@PathVariable(value="id") long id){
        return userDetailsService.getUserById(id);
    }
    
    @RequestMapping(value="/user", method=RequestMethod.DELETE, produces="application/json")
    public @ResponseBody User deleteUserById(@RequestParam(value="id", required=true) long id){
        return userDetailsService.deleteUserById(id);
    }
    
}
