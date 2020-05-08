package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessageNeedToDoRelationship;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 16:58 2020/5/5
 */
@Mapper
@Repository
public interface ProjectsMessageNeedToDoRelationshipMapper {
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
