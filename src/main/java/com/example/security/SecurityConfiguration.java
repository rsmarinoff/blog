/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author dilyan
 */
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    
    @Autowired
    UserDetailsService userDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.userDetailsService(userDetailsService()).csrf().disable()
      .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/posts/add").access("hasRole('ROLE_BLOGGER')")
        .antMatchers(HttpMethod.PUT, "/posts/update").access("hasRole('ROLE_BLOGGER')")
        .antMatchers(HttpMethod.DELETE, "/posts/delete").access("hasRole('ROLE_BLOGGER')")
        .antMatchers(HttpMethod.GET, "/posts/get/**").access("hasAnyRole('ROLE_BLOGGER', 'ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/users/add").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.PUT, "/users/update").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/users/delete").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/users/get/**").access("hasAnyRole('ROLE_ADMIN')")
        .anyRequest().permitAll()
        .and()
      .httpBasic().and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
