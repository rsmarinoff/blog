/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dilyan
 */
@Entity
public class Role implements GrantedAuthority, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userRole;

    
    protected Role(){
        
    }
    
    public Role(String userRole){
        this.userRole = userRole;
    }
    
    @Override
    public String getAuthority() {
       return userRole; 
    }
    
    
    public void setRole(String userRole){
        this.userRole = userRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
