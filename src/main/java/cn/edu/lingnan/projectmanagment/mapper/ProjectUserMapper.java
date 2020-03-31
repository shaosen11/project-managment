package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectUserMapper {
    /**
     * 查询所有项目用户信息
     * @return
     */
    List<ProjectsUser> getProjectUserList();

    /**
     * 添加项目用户信息
     * @param projectsUser
     * @return
     */
    boolean addProjectUser(ProjectsUser projectsUser);

    /**
     * 删除项目用户信息
     * @return
     */
    boolean deleteProjectUser(Integer id);

    /**
     * 删除项目用户信息通过项目id
     * @return
     */
    boolean deleteProjectUserByProjectsId(Integer id);

    /**
     * 修改项目用户信息
     * @return
     */
    boolean editProjectUser(ProjectsUser projectsUser);

    /**
     * 还原项目用户信息
     * @return
     */
    boolean reductionProjectUser(Integer id);

    /**
     * 查询所有已注销项目用户信息
     * @return
     */
    List<ProjectsUser> getDelProjectUserList();
}
