/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

import com.example.security.User;
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
    
    public BlogPost addPost(BlogPost post, User user){
        post.setAuthor(user.getUsername());
        return repository.save(post);
    }
    
    public List<BlogPost> readAll(){
        return repository.findAll();
    }
    
    public BlogPost readPostById(long id){
        return repository.findOne(id);
    }
    
    public BlogPost updatePost(long id, BlogPost post, User user){
        BlogPost blogPost = repository.findOne(id);
        if(blogPost == null){
            return new BlogPost();
        }
        if(!blogPost.getAuthor().equals(user.getUsername())){
            return new BlogPost();
        }
        blogPost.setTitle(post.getTitle());
        blogPost.setContent(post.getContent());
        return repository.save(blogPost);       
    }
    
    public BlogPost deltePostById(long id){
        BlogPost post = repository.findOne(id);
        if(post == null){
            return new BlogPost();
        }
        repository.delete(id);
        return post;
    }
    
    public BlogPost addCommentToPostById(long id, BlogPostComment commentToAdd, User user){
        BlogPost post = repository.findOne(id);
        if(post == null){
            return new BlogPost();
        }
        commentToAdd.setAuthor(user.getUsername());
        post.addComment(commentToAdd);
        return repository.save(post);
    }
    
    public BlogPost deleteCommentFromPostById(long postId, long commentId, User user){
        BlogPost post = repository.findOne(postId);
        if(post == null){
            return new BlogPost();
        }
        List<BlogPostComment> localComments = post.getComments();
        if(localComments.isEmpty()){
            return new BlogPost();
        }
        for(BlogPostComment comment : localComments){
            if(comment.getId() == commentId){
                localComments.remove(comment);
            }
        }
        return repository.save(post);
    }
}
