package com.example.cms.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter

@Table(name = "Events")
public class Events {

    @Id
    @NotEmpty
    private String eventCode;

    @NotEmpty
    private String eventName;

    @NotEmpty
    private String eventDate;

    public Events(String eventCode, String eventName, String eventDate){
        this.eventCode = eventCode;
        this.eventName = eventName;
        this.eventDate = eventDate;
    }

}