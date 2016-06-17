/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

import com.example.security.User;
import com.example.security.UserAuthentication;
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
public class BlogController {
    
    @Autowired
    private BlogPostService postService;
    
    @Autowired
    UserAuthentication authentication;
    
    @RequestMapping(value = "/posts", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<BlogPost> getAllBlogPosts(){
        return postService.readAll();
    }
    
    @RequestMapping(value = "/post/{id}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody BlogPost getPostById(@PathVariable(value="id") long id){
        return postService.readPostById(id);
    }
    
    @RequestMapping(value = "/post", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody BlogPost addPost(@RequestBody BlogPost post){
        return postService.addPost(post, (User) authentication.getPrincipal());
    }
    
    @RequestMapping(value = "/post", method=RequestMethod.PUT, consumes="application/json")
    public @ResponseBody BlogPost updatePost(@RequestParam(value="id", required=true) long id, 
                                             @RequestBody BlogPost post){
        return postService.updatePost(id, post, (User) authentication.getPrincipal());
    }
    
    @RequestMapping(value = "/post", method=RequestMethod.DELETE, produces="application/json")
    public @ResponseBody BlogPost deletePost(@RequestParam(value="id", required=true) long id){
        return postService.deltePostById(id, (User) authentication.getPrincipal());
    }
    
    @RequestMapping(value = "/comments/{postid}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody List<BlogPostComment> getCommentsForPostById(@PathVariable(value="postid") long postId){
        return postService.getCommentsForPost(postId);
    }
    
    @RequestMapping(value = "/comment/{postid}/{commentid}", method=RequestMethod.GET, produces="application/json")
    public @ResponseBody BlogPostComment getCommentById(@PathVariable(value="postid") long postId,
                                                        @PathVariable(value="commentid") long commentId){
        return postService.getCommentById(postId, commentId);
    }
    
    @RequestMapping(value = "/comment", method=RequestMethod.POST, consumes="application/json")
    public @ResponseBody BlogPost addComment(@RequestParam(value="id", required=true) long id,
                                             @RequestBody BlogPostComment comment){
        return postService.addCommentToPostById(id, comment, (User) authentication.getPrincipal());
    }
    
    @RequestMapping(value = "/comment", method=RequestMethod.PUT, consumes="application/json")
    public @ResponseBody BlogPost addComment(@RequestParam(value="postid", required=true) long postId,
                                             @RequestParam(value="commentid", required=true) long commentId,
                                             @RequestBody BlogPostComment comment){
        return postService.updateCommentForPostById(postId, commentId, comment, (User) authentication.getPrincipal());
    }
    
    @RequestMapping(value = "/comment", method=RequestMethod.DELETE, produces="application/json")
    public @ResponseBody BlogPost deleteComment(@RequestParam(value="postid", required=true) long postId,
                                                @RequestParam(value="commentid", required=true) long commentId){
        return postService.deleteCommentFromPostById(postId, commentId, (User) authentication.getPrincipal());
    }
}
