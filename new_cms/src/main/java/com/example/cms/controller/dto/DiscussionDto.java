package com.example.cms.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussionDto {
    private long discussionId;
    private String discussionContent;
    private long studentId;
}
