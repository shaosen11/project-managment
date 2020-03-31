package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 15:47 2020/3/28
 */
@Mapper
@Repository
public interface ProjectsUserMapper {
    /**
     * 通过用户Id查询所有项目Id
     * @param userId
     * @return
     */
    List<ProjectsUser> getProjectsByUserId(Integer userId);

    /**
     * 通过项目Id查找所有项目成员
     * @param projectId
     * @return
     */
    List<ProjectsUser> getUserByProjectId(Integer projectId);
}
