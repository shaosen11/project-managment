package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessageNeedToDoRelationship;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsMessageNeedToDoRelationshipMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsMessageNeedToDoRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 16:55 2020/5/5
 */
@Service
public class ProjectsMessageNeedToDoRelationshipServiceImpl implements ProjectsMessageNeedToDoRelationshipService {
    @Autowired
    ProjectsMessageNeedToDoRelationshipMapper projectsMessageNeedToDoRelationshipMapper;

    @Override
    public ProjectsMessageNeedToDoRelationship getByProjectMessageId(Integer projectMessageId) {
        return projectsMessageNeedToDoRelationshipMapper.getByProjectMessageId(projectMessageId);
    }

    @Override
    public ProjectsMessageNeedToDoRelationship getByDocumentId(Integer documentId) {
        return projectsMessageNeedToDoRelationshipMapper.getByDocumentId(documentId);
    }

    @Override
    public void insert(ProjectsMessageNeedToDoRelationship projectsMessageNeedToDoRelationship) {
        projectsMessageNeedToDoRelationshipMapper.insert(projectsMessageNeedToDoRelationship);
    }
}
