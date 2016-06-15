/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.user;

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
    private String Id;
    
    protected Role(){
        
    }
    
    public Role(String Id){
        this.Id = Id;
    }
    
    @Override
    public String getAuthority() {
       return Id; 
    }
    
}
