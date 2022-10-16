package com.blog.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidUserAndPassword extends RuntimeException {
    private String message;

    public InvalidUserAndPassword(String message) {
        super(String.format(message));
        this.message = message;

    }

}
