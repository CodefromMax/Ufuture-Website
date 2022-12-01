package com.example.cms.controller.exceptions;

public class DiscussionNotMatchException extends RuntimeException {
    public DiscussionNotMatchException(Long discussionId) {
        super("The student does not have access for deleting post" +discussionId);
    }
}
