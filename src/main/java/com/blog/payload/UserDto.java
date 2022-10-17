package com.blog.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {

    private int id;
    @NotEmpty
    @Size(min = 4, message = "Username must be minimum 4 chars!!")
    private String name;
    @NotEmpty
    @Email(message = "Email Address is not valid!!")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "Passwordmust be min 4 char and max 10 chars!!")
    private String password;
    @NotEmpty
    private String about;
    private Set<RoleDto> roles=new HashSet<>();

}
