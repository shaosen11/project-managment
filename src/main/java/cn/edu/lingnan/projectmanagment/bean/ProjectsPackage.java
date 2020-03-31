package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:48 2020/3/28
 */
@Data
public class ProjectsPackage {
    private Integer id;
    private Integer projectId;
    private Integer packageId;
    private String packageName;
    private String documentName;
    private Date createTime;
    private Integer userId;
}
