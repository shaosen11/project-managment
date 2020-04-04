package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsCodeLine;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 19:57 2020/4/3
 */
@Mapper
@Repository
public interface ProjectsCodeLineMapper {
    /**
     * 查询今天有没有记录
     * @param projectsId
     * @return
     */
    ProjectsCodeLine getProjectsCodeLineByProjectIdAndToday(Integer projectsId);

    /**
     * 查询最近有没有记录
     * @param projectsId
     * @return
     */
    ProjectsCodeLine getProjectsCodeLineByProjectIdAndLastDay(Integer projectsId);

    /**
     * 插入一条项目代码行记录
     * @param bean
     * @return
     */
    boolean insert(ProjectsCodeLine bean);

    /**
     * 更新一条代码行记录
     * @param bean
     * @return
     */
    boolean update(ProjectsCodeLine bean);

    /**
     * 查询项目所有代码行记录
     * @param projectId
     * @return
     */
    List<ProjectsCodeLine> getAllProjectsCodeLineByProjectId(Integer projectId);
}
