package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;

import java.util.List;

public interface ProjectUserService {
    /**
     * 查询所有项目用户信息
     * @return
     */
    List<ProjectsUser> getProjectUserList();

    /**
     * 添加项目用户信息
     * @param projectsUser
     * @return
     */
    boolean addProjectUser(ProjectsUser projectsUser);

    /**
     * 删除项目用户信息
     * @return
     */
    boolean deleteProjectUser(Integer id);

    /**
     * 删除项目用户信息通过项目id
     * @return
     */
    boolean deleteProjectUserByProjectsId(Integer id);

    /**
     * 修改项目用户信息
     * @return
     */
    boolean editProjectUser(ProjectsUser projectsUser);

    /**
     * 还原项目用户信息
     * @return
     */
    boolean reductionProjectUser(Integer id);

    /**
     * 查询所有已注销项目用户信息
     * @return
     */
    List<ProjectsUser> getDelProjectUserList();

    /**
     * 通过用户Id和项目Id查询用户
     * @param userId
     * @param projectId
     * @return
     */
    ProjectsUser getByUserIdAndProjectId(Integer userId, Integer projectId);
}
