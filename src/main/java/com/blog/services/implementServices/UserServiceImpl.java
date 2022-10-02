package com.blog.services.implementServices;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.UserDto;
import com.blog.repository.userRepo;
import com.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private userRepo userrepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User savedUser = this.userrepo.save(user);
        return this.userToDto(savedUser);
    }

    @Override
    public UserDto updateUser(UserDto userdto, Integer id) {
        User user = this.userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setName(userdto.getName());
        user.setEmail(userdto.getEmail());
        user.setPassword(userdto.getPassword());
        user.setAbout(userdto.getAbout());
        User updatedUser = this.userrepo.save(user);
        UserDto userDto1 = this.userToDto(updatedUser);
        return userDto1;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userrepo.findAll();
        List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public void deleteUser(Integer id) {
        User user = this.userrepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        this.userrepo.delete(user);
    }

    private User dtoToUser(UserDto dtoObj) {
        User user = this.modelMapper.map(dtoObj, User.class);
        // User user = new User();
        // user.setId(dtoObj.getId());
        // user.setName(dtoObj.getName());
        // user.setEmail(dtoObj.getEmail());
        // user.setPassword(dtoObj.getPassword());
        // user.setAbout(dtoObj.getAbout());
        return user;
    }

    private UserDto userToDto(User userObj) {
        UserDto user = this.modelMapper.map(userObj, UserDto.class);
        // UserDto user = new UserDto();
        // user.setId(userObj.getId());
        // user.setName(userObj.getName());
        // user.setEmail(userObj.getEmail());
        // user.setPassword(userObj.getPassword());
        // user.setAbout(userObj.getAbout());
        return user;
    }

}
