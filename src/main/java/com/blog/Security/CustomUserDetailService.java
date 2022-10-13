package com.blog.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.repository.userRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
//LOADING USER FROM DATABASE BY USERNAME
@Autowired
private userRepo userRepo;

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
  User user=  this.userRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User", "email"+email, 0));
    return user;
}
}