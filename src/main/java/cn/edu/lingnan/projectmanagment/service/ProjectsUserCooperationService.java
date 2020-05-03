package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserCooperation;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:03 2020/4/27
 */
public interface ProjectsUserCooperationService {
    /**
     * 查找项目有没有邀请过此人，并且未完成邀请
     *
     * @param projectId
     * @param inProjectUserId
     * @param notInProjectUserId
     * @param inviteFlag
     * @param finishFlag
     * @return
     */
    ProjectsUserCooperation getByProjectIdAndInProjectUserIdAndNotInProjectUserIdAndInviteAndFinish(Integer projectId, Integer inProjectUserId, Integer notInProjectUserId, Integer inviteFlag, Integer finishFlag);

    /**
     * 插入一条邀请记录
     *
     * @param projectsUserCooperation
     * @return
     */
    void insert(ProjectsUserCooperation projectsUserCooperation);

    /**
     * 修改状态
     *
     * @param projectsUserCooperation
     * @return
     */
    boolean update(ProjectsUserCooperation projectsUserCooperation);

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    ProjectsUserCooperation getById(Integer id);

    /**
     * 获取项目对个人的所有邀请
     *
     * @param projectId
     * @param notInProjectUserId
     * @param inviteFlag
     * @param finishFlag
     * @return
     */
    List<ProjectsUserCooperation> getByProjectIdAndNotInProjectUserIdAndInviteAndFinish(Integer projectId, Integer notInProjectUserId, Integer inviteFlag, Integer finishFlag);
}
