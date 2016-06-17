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
        .antMatchers(HttpMethod.POST, "/post").access("hasRole('ROLE_BLOGGER')")
        .antMatchers(HttpMethod.PUT, "/post").access("hasRole('ROLE_BLOGGER')")
        .antMatchers(HttpMethod.DELETE, "/post").access("hasRole('ROLE_BLOGGER')")
        .antMatchers(HttpMethod.GET, "/post/**").access("hasAnyRole('ROLE_BLOGGER', 'ROLE_USER')")
        .antMatchers(HttpMethod.GET, "/posts").access("hasAnyRole('ROLE_BLOGGER', 'ROLE_USER')")
        .antMatchers(HttpMethod.POST, "/user").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.PUT, "/user").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.DELETE, "/user").access("hasRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/user/**").access("hasAnyRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.GET, "/users").access("hasAnyRole('ROLE_ADMIN')")
        .antMatchers(HttpMethod.POST, "/comment").authenticated()
        .antMatchers(HttpMethod.PUT, "/comment").authenticated()
        .antMatchers(HttpMethod.GET, "/comment").authenticated()
        .antMatchers(HttpMethod.GET, "/comments").authenticated()
        .antMatchers(HttpMethod.DELETE, "/comment").authenticated()
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
