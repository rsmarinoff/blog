/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.util.List;

/**
 *
 * @author dilyan
 */
public class UserDTO {
    
    private String username;
    private String password;
    private String roles;
    
    protected UserDTO(){
        
    }
    
    public UserDTO(String username, String password, String roles){
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    /**
     * @return the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param user the user to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the roles
     */
    public String getRoles() {
        return roles;
    }

    /**
     * @param roles the roles to set
     */
    public void setRoles(String roles) {
        this.roles = roles;
    }
    
    
}
