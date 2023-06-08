package com.ling.project_backend.domain;

import lombok.Data;

import java.sql.Date;

/**
 * 社区详情
 */
@Data
public class CommunityDetail {

    private Integer communityId;
    private String communityName;
    private Integer userId;
    private Date communityTime;
    private String communityTag;

}
