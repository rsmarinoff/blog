/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author dilyan
 */
@Service
public class BlogPostService {
    
    @Autowired
    private BlogPostRepository repository;
    
    public BlogPost addPost(BlogPost post){
        return repository.save(post);
    }
    
    public List<BlogPost> readAll(){
        return repository.findAll();
    }
    
    public BlogPost readPostById(long id){
        return repository.findOne(id);
    }
}
