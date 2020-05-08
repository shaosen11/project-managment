package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessageNeedToDoRelationship;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 16:55 2020/5/5
 */
public interface ProjectsMessageNeedToDoRelationshipService {
    /**
     * 通过消息id查询需要处理的事情
     *
     * @param projectMessageId
     * @return
     */
    ProjectsMessageNeedToDoRelationship getByProjectMessageId(Integer projectMessageId);

    /**
     * 通过documentId查询需要处理的事情
     *
     * @param documentId
     * @return
     */
    ProjectsMessageNeedToDoRelationship getByDocumentId(Integer documentId);

    /**
     * 插入一条关系
     *
     * @param projectsMessageNeedToDoRelationship
     */
    void insert(ProjectsMessageNeedToDoRelationship projectsMessageNeedToDoRelationship);
}
