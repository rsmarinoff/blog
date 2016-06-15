/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

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
@RequestMapping("/posts")
public class BlogController {
    
    @Autowired
    private BlogPostService postService;
    
    @RequestMapping(value = "/get/all", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<BlogPost> getAllBlogPosts(){
        return postService.readAll();
    }
    
    @RequestMapping(value = "/get/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody BlogPost getPostById(@PathVariable(value="id") long id){
        return postService.readPostById(id);
    }
    
    @RequestMapping(value = "/add", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody BlogPost addPost(@RequestBody BlogPost post){
        return postService.addPost(post);
    }
    
    @RequestMapping(value = "/update", method=RequestMethod.PUT, consumes="application/json")
    public @ResponseBody BlogPost updatePost(@RequestParam(value="id", required=true) long id, 
                                             @RequestBody BlogPost post){
        BlogPost blogPost = postService.readPostById(id);
        if(blogPost == null){
            return new BlogPost();
        }
        return postService.addPost(new BlogPost(id, post.getTitle(), post.getAuthor(), post.getContent()));
    }
    
}
