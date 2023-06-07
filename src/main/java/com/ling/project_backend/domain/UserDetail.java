package com.ling.project_backend.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ling.project_backend.common.Url;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用户详细信息
 * 对应表：users_detail
 */
@Data
public class UserDetail {
    private Integer userId;
    private String userName;
    private String userSex;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date userBirth;
    private String userIntro;
    private String userImage;

    public UserDetail(){

    }

    public UserDetail(Integer userId) {
        this.userId = userId;
        this.userName = "用户"+userId;
        this.userSex = "保密";
        this.userBirth = Calendar.getInstance().getTime();
        this.userIntro = "";
        this.userImage = Url.DEFAULT_IMAGE;
    }
}
