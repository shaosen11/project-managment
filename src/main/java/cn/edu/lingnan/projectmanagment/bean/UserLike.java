package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserLike {
    private Integer id;
    private Integer userId;
    private Integer projectsId;
    private Integer deleteFlag;
    private Date clickTime;
}

