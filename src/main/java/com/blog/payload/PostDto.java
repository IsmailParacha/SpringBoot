package com.blog.payload;

import java.util.Date;

import javax.validation.constraints.*;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor

public class PostDto {
    @NotEmpty
    @Size(min = 5, message = "Title must be minimum 5 chars!")
    private String title;
    @Size(min = 5, message = "Content must be minimum 5 chars!")
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDto user;
    private CategoryDto category;

    
}