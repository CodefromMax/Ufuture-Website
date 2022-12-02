package com.example.cms.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class EventDto {
    private String eventName;
    private String eventDate;
    private String location;
    private String universityId;
    private String universityUserId;
}
