package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessage;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:50 2020/4/17
 */
public interface ProjectsMessageService {
    /**
     * 通过projectId和userId查询所有个人消息
     *
     * @param projectId
     * @param userId
     * @param offset
     * @param pageSize
     * @return
     */
    List<ProjectsMessage> getByProjectIdAndUserId(Integer projectId, Integer userId, Integer offset, Integer pageSize);

    /**
     * 插入一条项目信息
     *
     * @param projectsMessage
     */
    void insert(ProjectsMessage projectsMessage);

    /**
     * 通过projectId查询所有项目消息
     *
     * @param projectId
     * @param offset
     * @param pageSize
     * @return
     */
    List<ProjectsMessage> getByProjectId(Integer projectId, Integer offset, Integer pageSize);

    /**
     * 修改项目消息
     *
     * @param projectsMessage
     * @return
     */
    boolean update(ProjectsMessage projectsMessage);

    /**
     * 修改项目消息已读
     *
     * @param projectMessageId
     * @return
     */
    boolean updateProjectMessageIsReadByProjectMessageId(Integer projectMessageId);

    /**
     * 通过projectId查询所有待办消息
     *
     * @param projectId
     * @param offset
     * @param pageSize
     * @return
     */
    List<ProjectsMessage> getAllNeedToByProjectId(Integer projectId, Integer offset, Integer pageSize);

    /**
     * 通过id查找项目消息
     *
     * @param id
     * @return
     */
    ProjectsMessage getById(Integer id);

    /**
     * 获取项目消息数量
     *
     * @param projectId
     * @param userId
     * @return
     */
    Integer projectMessageCount(Integer projectId, Integer userId);

    /**
     * 获取项目消息数量
     *
     * @param projectId
     * @return
     */
    Integer projectMessageNeedToDoCount(Integer projectId);
}

