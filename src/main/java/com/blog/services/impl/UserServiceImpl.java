package com.blog.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.blog.entities.User;
import com.blog.payload.UserDto;
import com.blog.repository.userRepo;
import com.blog.services.UserService;

public class UserServiceImpl implements UserService {
    @Autowired
    private userRepo userrepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userrepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto user, Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDto getUserById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        // TODO Auto-generated method stub

    }

    private User dtoToUser(UserDto dtoObj) {
        User user = new User();
        user.setId(dtoObj.getId());
        user.setName(dtoObj.getName());
        user.setEmail(dtoObj.getEmail());
        user.setAbout(dtoObj.getAbout());
        return user;
    }

    private UserDto userToDto(User userObj) {
        UserDto user = new UserDto();
        user.setId(userObj.getId());
        user.setName(userObj.getName());
        user.setEmail(userObj.getEmail());
        user.setAbout(userObj.getAbout());
        return user;
    }

}
