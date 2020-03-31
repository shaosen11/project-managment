package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

@Data
public class ProjectsFunction {
    private Integer id;
    private Integer projectsId;
    private String projectName;
    private Integer functionId;
    private String functionName;
    private Integer functionStatus;
    private Integer userId;
    private String userName;
    private Integer deleteFlag;
}
