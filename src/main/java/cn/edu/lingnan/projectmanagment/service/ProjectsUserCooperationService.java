package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserCooperation;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:03 2020/4/27
 */
public interface ProjectsUserCooperationService {
    /**
     * 查找有没有邀请过此人
     *
     * @param projectId
     * @param inProjectUserId
     * @param notInProjectUserId
     * @param invite
     * @return
     */
    ProjectsUserCooperation getByProjectIdAndInProjectUserIdAndNotInProjectUserIdAndInvite(Integer projectId, Integer inProjectUserId, Integer notInProjectUserId, Integer invite);

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
}
