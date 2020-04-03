package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsCodeLine;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 19:56 2020/4/3
 */
public interface ProjectsCodeLineService {
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
}
