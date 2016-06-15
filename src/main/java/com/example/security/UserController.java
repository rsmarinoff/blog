/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dilyan
 */
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    
    @RequestMapping(value="/add", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody User addUser(@RequestBody UserDTO user){
        return userDetailsService.addUser(new User(user.getUsername(), user.getPassword(), user.getRoles()));
    }
    
    @RequestMapping(value="/get/all", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<User> listAllUsers(){
        return userDetailsService.listAll();
    }
    
}
