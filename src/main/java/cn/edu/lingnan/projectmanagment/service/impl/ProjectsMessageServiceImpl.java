package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessage;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsMessageMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:51 2020/4/17
 */
@Service
public class ProjectsMessageServiceImpl implements ProjectsMessageService {
    @Autowired
    ProjectsMessageMapper projectsMessageMapper;
    @Override
    public List<ProjectsMessage> getByProjectIdAndUserId(Integer projectId, Integer userId, Integer offset, Integer pageSize) {
        return projectsMessageMapper.getByProjectIdAndUserId(projectId, userId, offset, pageSize);
    }

    @Override
    public void insert(ProjectsMessage projectsMessage) {
        projectsMessageMapper.insert(projectsMessage);
    }

    @Override
    public List<ProjectsMessage> getByProjectId(Integer projectId, Integer offset, Integer pageSize) {
        return projectsMessageMapper.getByProjectId(projectId, offset, pageSize);
    }

    @Override
    public boolean update(ProjectsMessage projectsMessage) {
        return projectsMessageMapper.update(projectsMessage);
    }

    @Override
    public boolean updateProjectMessageIsReadByProjectMessageId(Integer projectMessageId) {
        return projectsMessageMapper.updateProjectMessageIsReadByProjectMessageId(projectMessageId);
    }
}
