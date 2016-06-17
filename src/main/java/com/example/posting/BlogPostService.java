/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

import com.example.security.User;
import java.util.ArrayList;
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
        if(!belongsToUser(post, user)){
            return new BlogPost();
        }
        blogPost.setTitle(post.getTitle());
        blogPost.setContent(post.getContent());
        return repository.save(blogPost);       
    }
    
    public BlogPost deltePostById(long id, User user){
        BlogPost post = repository.findOne(id);
        if(post == null){
            return new BlogPost();
        }
        if(!belongsToUser(post, user)){
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
        if(post.getComments().isEmpty()){
            return new BlogPost();
        }
        if(belongsToUser(post.getCommentById(commentId), user)){
            post.deleteCommentById(commentId);
            return repository.save(post);
        }
        if(belongsToUser(post, user)){
            post.deleteCommentById(commentId);
            return repository.save(post);
        }
        return new BlogPost();
    }
    
    public BlogPost updateCommentForPostById(long postId, long commentId, BlogPostComment newComment, User user){
        BlogPost post = repository.findOne(postId);
        if(post == null){
            return new BlogPost();
        }
        List<BlogPostComment> localComments = post.getComments();
        if(localComments.isEmpty()){
            return new BlogPost();
        }
        if(!belongsToUser(post.getCommentById(commentId), user)){
            return new BlogPost();
        }
        newComment.setAuthor(user.getUsername());
        post.updateCommentById(postId, newComment);
        return repository.save(post);
    }
    
    public BlogPostComment getCommentById(long postId, long commentId){
        BlogPost post = repository.findOne(postId);
        if(post == null){
            return new BlogPostComment();
        }
        if(post.getComments().isEmpty()){
            return new BlogPostComment();
        }
        return post.getCommentById(commentId);
    }
    
    public List<BlogPostComment> getCommentsForPost(long postId){
        BlogPost post = repository.findOne(postId);
        if(post == null){
            return new ArrayList<>();
        }
        return post.getComments();
    }
    
    private boolean belongsToUser(BlogPost post, User user){
        return post.getAuthor().equals(user.getUsername());
    }
    
    private boolean belongsToUser(BlogPostComment comment, User user){
        return comment.getAuthor().equals(user.getUsername());
    }
}
