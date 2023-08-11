package com.project.ecasa.Exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
       super("Resource Not Found");
    }

}
