package com.ling.project_backend.domain;

import lombok.Data;

@Data
public class User {
    private Integer userId;
    private String userName;
    private String userEmail;
    private String password;
}
