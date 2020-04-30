package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserCooperation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:09 2020/4/27
 */
@Mapper
@Repository
public interface ProjectsUserCooperationMapper {
    /**
     * 查找有没有邀请过此人
     * @param projectId
     * @param notInProjectUserId
     * @return
     */
    ProjectsUserCooperation getByProjectIdAndNotInProjectUserId(Integer projectId, Integer notInProjectUserId);

    /**
     * 插入一条邀请记录
     * @param projectsUserCooperation
     * @return
     */
    boolean insert(ProjectsUserCooperation projectsUserCooperation);

    /**
     * 修改状态
     * @param projectsUserCooperation
     * @return
     */
    boolean update(ProjectsUserCooperation projectsUserCooperation);
}
