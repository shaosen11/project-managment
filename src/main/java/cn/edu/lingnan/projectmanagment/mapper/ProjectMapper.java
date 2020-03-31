package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectMapper {
    /**
     * 查询一条项目信息
     * @return
     */
    Projects getById(Integer id);

    /**
     * 查询所有项目信息
     * @return
     */
    List<Projects> getProjectList();

    /**
     * 通过userId查询所有项目
     * @return
     */
    List<Projects> getProjectListByUserId(Integer userId);

    /**
     * 添加项目
     * @param project
     * @return
     */
    boolean addProject(Projects project);

    /**
     * 删除项目
     * @return
     */
    boolean deleteProject(Integer id);

    /**
     * 修改项目信息
     * @return
     */
    boolean editProject(Projects project);

    /**
     * 还原项目
     * @return
     */
    boolean reductionProject(Integer id);

    /**
     * 查询所有已注销项目信息
     * @return
     */
    List<Projects> getDelProjectList();
}
