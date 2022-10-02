package com.blog.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int id;
    @NotEmpty
    @Size(min = 4, max = 100, message = "Title must  be min 4 Chars")
    private String title;
    @NotEmpty
    private String description;
}
