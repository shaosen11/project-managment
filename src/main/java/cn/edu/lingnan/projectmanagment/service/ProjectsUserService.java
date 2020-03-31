package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 15:44 2020/3/28
 */
public interface ProjectsUserService {
    /**
     * 通过用户Id查询所有项目
     * @param userId
     * @return
     */
    List<ProjectsUser> getProjectsByUserId(Integer userId);

    /**
     * 通过项目查找所有项目成员
     * @param projectId
     * @return
     */
    List<ProjectsUser> getUserByProjectId(Integer projectId);


}
