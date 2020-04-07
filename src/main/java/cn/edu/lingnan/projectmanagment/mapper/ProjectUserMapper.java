package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Echarts;
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

    /**
     * 通过用户Id和项目Id查询用户
     * @param userId
     * @param projectId
     * @return
     */
    ProjectsUser getByUserIdAndProjectId(Integer userId, Integer projectId);

    /**
     * 通过Id查询
     * @return
     */
    ProjectsUser getById(Integer id);

    /**
     * 通过Id查询已注销的记录
     * @return
     */
    ProjectsUser getDelById(Integer id);

    /**
     * 获取代码贡献量
     * @param projectId
     * @return
     */
    List<Echarts> getCodeDevote(Integer projectId);

    /**
     * 获取代码上传次数
     * @param projectId
     * @return
     */
    List<Echarts> getCodeInsert(Integer projectId);
}
