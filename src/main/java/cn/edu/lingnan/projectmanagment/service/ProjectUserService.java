package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.Echarts;
import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;

import java.util.List;

public interface ProjectUserService {
    /**
     * 查询所有项目用户信息
     *
     * @return
     */
    List<ProjectsUser> getProjectUserList();

    /**
     * 通过Id查询
     *
     * @return
     */
    ProjectsUser getById(Integer id);

    /**
     * 通过Id查询已注销的记录
     *
     * @return
     */
    ProjectsUser getDelById(Integer id);

    /**
     * 添加项目用户信息
     *
     * @param projectsUser
     * @return
     */
    boolean addProjectUser(ProjectsUser projectsUser);

    /**
     * 删除项目用户信息
     *
     * @return
     */
    boolean deleteProjectUser(Integer id);

    /**
     * 删除项目用户信息通过项目id
     *
     * @return
     */
    boolean deleteProjectUserByProjectsId(Integer id);

    /**
     * 修改项目用户信息
     *
     * @return
     */
    boolean editProjectUser(ProjectsUser projectsUser);

    /**
     * 还原项目用户信息
     *
     * @return
     */
    boolean reductionProjectUser(Integer id);

    /**
     * 查询所有已注销项目用户信息
     *
     * @return
     */
    List<ProjectsUser> getDelProjectUserList();

    /**
     * 通过用户Id和项目Id查询用户
     *
     * @param userId
     * @param projectId
     * @return
     */
    ProjectsUser getByUserIdAndProjectId(Integer userId, Integer projectId);

    /**
     * 获取代码贡献量
     *
     * @param projectId
     * @return
     */
    List<Echarts> getCodeDevote(Integer projectId);

    /**
     * 获取代码上传次数
     *
     * @param projectId
     * @return
     */
    List<Echarts> getCodeInsert(Integer projectId);

    /**
     * 查询项目总共人数
     *
     * @param projectId
     * @return
     */
    Integer getCountByProjectId(Integer projectId);

    /**
     * 通过项目id查找所有项目成员
     *
     * @param projectId
     * @return
     */
    List<ProjectsUser> getAllProjectsUserByProjectId(Integer projectId, Integer offset, Integer pageSize);

    /**
     * 查询项目总共人数
     *
     * @param projectId
     * @return
     */
    Integer getCountNoInProjectByProjectId(Integer projectId);

    /**
     * 通过项目id查找不在项目的成员
     *
     * @param projectId
     * @return
     */
    List<MyUserDetails> getProjectsUserNoInProjectByProjectId(Integer projectId, Integer offset, Integer pageSize);

    /**
     * 通过userId查询
     *
     * @return
     */
    List<ProjectsUser> getAllProjectByUserId(Integer userId);

    /**
     * 获取项目各类人数
     * @param projectId
     * @param dutyCode
     * @return
     */
    Integer getCountByProjectIdAndDuty(Integer projectId, Integer dutyCode);
}

