package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserClick {
    private Integer id;
    private Integer userId;
    private Integer projectsId;
    private Integer deleteFlag;
    private Date clickTime;
}

