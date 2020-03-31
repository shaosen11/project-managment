package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 12:39 2020/3/29
 */
@Data
public class ProjectsPackageList {
    private Integer projectId;
    private Integer packageId;
    private String packageName;
    private List<ProjectsPackage> projectsPackageList;
}
