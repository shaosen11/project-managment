package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserStore {
    private Integer id;
    private Integer userId;
    private Integer projectsId;
    private Integer deleteFlag;
    private Date storeTime;
}

