package com.blog.exception;

public class ResourceNotFoundException extends RuntimeException {
    String resourceName;
    String fieldName;
    long fieldvalue;

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldvalue) {
        super(String.format("%s not fount with %s : %l", resourceName, fieldName, fieldvalue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldvalue = fieldvalue;
    }

}
