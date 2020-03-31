package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsFunction;

import java.util.List;

public interface ProjectsFunctionService {
    /**
     * 查询所有项目功能点信息
     * @return
     */
    List<ProjectsFunction> getProjectFunctionList();

    /**
     * 添加项目功能点信息前查找该项目最大功能点id
     * @return
     */
    Integer findMaxFunctionId(Integer id);

    /**
     * 添加项目功能点信息
     * @param projectsFunction
     * @return
     */
    boolean addProjectFunction(ProjectsFunction projectsFunction);

    /**
     * 删除项目功能点信息
     * @return
     */
    boolean deleteProjectFunction(Integer id);

    /**
     * 删除项目功能点信息通过ProjectsId
     * @return
     */
    boolean deleteProjectFunctionByProjectsId(Integer id);

    /**
     * 修改项目功能点信息
     * @return
     */
    boolean editProjectFunction(ProjectsFunction projectsFunction);

    /**
     * 还原项目功能点信息
     * @return
     */
    boolean reductionProjectFunction(Integer id);

    /**
     * 查询所有已注销项目功能点信息
     * @return
     */
    List<ProjectsFunction> getDelProjectFunctionList();
}