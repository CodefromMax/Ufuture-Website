package com.example.cms.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {
    private long discussionId;
    private String contents;
    private String commenterId;
}
