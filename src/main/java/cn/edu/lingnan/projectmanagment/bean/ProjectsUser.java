package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 15:38 2020/3/28
 */
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
