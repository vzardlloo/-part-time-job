package com.mall.zhangheng.dto;

import lombok.Data;

@Data
public class UserLoginParam {

    private String name;

    private String password;

    private String verifycode;

}
