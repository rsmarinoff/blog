/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author dilyan
 */
@Entity
public class Role implements GrantedAuthority{
    
    @Id
    private String id;
    
    protected Role(){
        
    }
    
    public Role(String id){
        this.id = id;
    }
    
    @Override
    public String getAuthority() {
       return id; 
    }
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
}
