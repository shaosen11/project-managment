package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

@Data
public class ProjectsUser {
    private Integer id;
    private Integer projectsId;
    private Integer userId;
    private Integer codeUpdate;
    private Integer deleteFlag;
    private MyUserDetails myUserDetails;
    private Projects projects;
}