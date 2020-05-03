package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserCooperation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:09 2020/4/27
 */
@Mapper
@Repository
public interface ProjectsUserCooperationMapper {
    /**
     * 查找项目有没有邀请过此人，并且未完成邀请
     *
     * @param projectId
     * @param inProjectUserId
     * @param notInProjectUserId
     * @param inviteFlag
     * @param finishFlag
     * @return
     */
    ProjectsUserCooperation getByProjectIdAndInProjectUserIdAndNotInProjectUserIdAndInviteAndFinish(Integer projectId, Integer inProjectUserId, Integer notInProjectUserId, Integer inviteFlag, Integer finishFlag);

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

    /**
     * 通过id查找
     *
     * @param id
     * @return
     */
    ProjectsUserCooperation getById(Integer id);

    /**
     * 获取项目对个人的所有邀请
     *
     * @param projectId
     * @param notInProjectUserId
     * @param inviteFlag
     * @param finishFlag
     * @return
     */
    List<ProjectsUserCooperation> getByProjectIdAndNotInProjectUserIdAndInviteAndFinish(Integer projectId, Integer notInProjectUserId, Integer inviteFlag, Integer finishFlag);
}
