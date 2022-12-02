package com.example.cms.controller.vo;

import com.example.cms.model.entity.User;
import lombok.Data;

@Data
public class UserVO  extends User {
    private Integer isStudent;
}
