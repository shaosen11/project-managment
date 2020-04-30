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
     * 通过projectId和userId查询所有消息
     * @param userId
     * @return
     */
    List<ProjectsMessage> getByProjectIdAndUserId(Integer projectId, Integer userId, Integer offset, Integer pageSize);
}

