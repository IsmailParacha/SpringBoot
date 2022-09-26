package com.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.payload.ApiResponse;
import com.blog.payload.UserDto;
import com.blog.services.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {
    @Autowired
    private UserService userServices;

    // POST-create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userdto) {
        UserDto createUserDto = this.userServices.createUser(userdto);
        return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);
    }

    // PUT-update user
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userdto, @PathVariable Integer id) {
        UserDto updateUser = this.userServices.updateUser(userdto, id);
        return ResponseEntity.ok(updateUser);

    }

    // DELETE-delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
        this.userServices.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
    }

    // GET- multiple users get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(this.userServices.getAllUsers());
    }

    // GET- single user get
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUsers(@PathVariable Integer id) {
        return ResponseEntity.ok(this.userServices.getUserById(id));
    }

}
