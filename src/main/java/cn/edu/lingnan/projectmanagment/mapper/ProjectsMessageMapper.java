package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:54 2020/4/17
 */
@Mapper
@Repository
public interface ProjectsMessageMapper {
    /**
     * 通过projectId和userId查询所有消息
     *
     * @param userId
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
     * 修改项目已读
     * @param projectMessageId
     * @return
     */
    boolean updateProjectMessageIsReadByProjectMessageId(Integer projectMessageId);
}

