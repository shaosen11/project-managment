package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectsUser {
    private Integer id;
    private Integer projectsId;
    private Integer userId;
    private Integer dutyCode;
    private Integer codeDevoteLine;
    private Double codeDevoteLineRatio;
    private Integer codeUpdate;
    private Double codeUpdateRatio;
    private Integer deleteFlag;
    private MyUserDetails myUserDetails;
    private ProjectsUserDuty projectsUserDuty;
    private Projects projects;
    private Date joinTime;
}

