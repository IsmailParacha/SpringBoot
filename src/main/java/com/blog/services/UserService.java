package com.blog.services;

import java.util.List;

import com.blog.payload.UserDto;

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto updateUser(UserDto user, Integer id);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    void deleteUser(Integer id);
    UserDto registerUser(UserDto userDto);
}
