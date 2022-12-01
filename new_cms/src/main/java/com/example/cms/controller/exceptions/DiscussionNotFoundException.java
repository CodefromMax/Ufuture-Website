package com.example.cms.controller.exceptions;

public class DiscussionNotFoundException extends RuntimeException{
    public DiscussionNotFoundException(long id) {super("Could not find discussion " + id);}
}
