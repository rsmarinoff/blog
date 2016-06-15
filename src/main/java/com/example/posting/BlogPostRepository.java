/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author dilyan
 */
public interface BlogPostRepository extends JpaRepository<BlogPost, Long>{
    
}
