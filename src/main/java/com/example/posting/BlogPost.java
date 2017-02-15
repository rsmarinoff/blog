/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.posting;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 *
 * @author dilyan
 */
@Entity
public class BlogPost implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String author;
    private String content;

    @Column(name = "created_on")
    private Date createdOn;
    @Column(name = "updated_on")
    private Date updatedOn;
    @Column(name = "created_at")
    private Time createdAt;
    @Column(name = "updated_at")
    private Time updatedAt;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<BlogPostComment> comments = new ArrayList<>();

    protected BlogPost() {

    }

    /**
     *
     * Something, something...
     *
     */
    public BlogPost(long id, String title, String author, String content) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author the author to set
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    public void addComment(BlogPostComment commentToAdd) {
        comments.add(commentToAdd);
    }

    public List<BlogPostComment> getComments() {
        return comments;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @return the updatedAt
     */
    public Date getUpdatedOn() {
        return updatedOn;
    }

    @PrePersist
    void createdOn() {
        this.createdOn = this.updatedOn = new Date(Calendar.getInstance().getTimeInMillis());
        this.createdAt = this.updatedAt = new Time(Calendar.getInstance().getTimeInMillis());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedOn = new Date(Calendar.getInstance().getTimeInMillis());
        this.updatedAt = new Time(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * @return the createdAt
     */
    public Time getCreatedAt() {
        return createdAt;
    }

    /**
     * @return the updatedAt
     */
    public Time getUpdatedAt() {
        return updatedAt;
    }

    public void deleteCommentById(long id) {
        for (BlogPostComment comment : comments) {
            if (comment.getId() == id) {
                comments.remove(comment);
                return;
            }
        }
    }

    public void updateCommentById(long id, BlogPostComment newComment) {
        deleteCommentById(id);
        newComment.setId(id);
        comments.add(newComment);
    }

    public BlogPostComment getCommentById(long id) {
        for (BlogPostComment comment : comments) {
            if (comment.getId() == id) {
                return comment;
            }
        }
        return new BlogPostComment(0, "", "");
    }

}
