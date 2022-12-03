package com.example.cms.controller.dto;

import com.example.cms.model.entity.Interest_list;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InterestListDto {
    private String studentId;
    private String comment;
    private String universityId;
    private String listOrder;
    // 0 Qs_rankings 1 Cwur_rankings 2 Times_rankings
    private Integer type;
}
