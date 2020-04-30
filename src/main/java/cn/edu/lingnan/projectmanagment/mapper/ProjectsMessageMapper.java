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
     * @param userId
     * @return
     */
    List<ProjectsMessage> getByProjectIdAndUserId(Integer projectId, Integer userId, Integer offset, Integer pageSize);
}

