package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectsFunctionService {
    /**
     * 查询所有项目功能点信息
     * @return
     */
    List<ProjectsFunction> getProjectFunctionList();

    /**
     * 查询一条项目功能点信息
     * @return
     */
    ProjectsFunction getOneProjectFunction(Integer id);

    /**
     * 查询一条项目功能点信息,包括已注销
     * @return
     */
    ProjectsFunction getById(Integer id);

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

    /**
     * 查询项目功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectId(Integer id);

    /**
     * 通过项目id查询功能点数量
     * @return
     */
    Integer countByProjectIdAndStatus(Integer id,Integer functionStatus);

    /**
     * 通过项目id和status查询指派给我的功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectIdAndRealizeUserId(Integer projectId,Integer userId,Integer functionStatus);

    /**
     * 通过项目id和status查询我发布的功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectIdAndPublishUserId(Integer projectId,Integer userId,Integer functionStatus);

    /**
     * 通过项目id和status查询我参与的功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectIdAndUserId(Integer projectId,Integer userId,Integer functionStatus);

    /**
     * 通过项目id查询全部的功能
     * @return
     */
    List<ProjectsFunction> getAllFunctionByProjectId(Integer id);

    /**
     * 添加项目功能点信息前查找该项目最大功能点id
     * @return
     */
    Integer findMaxFunctionId(Integer id);

    /**
     * 分页--全部功能
     * @return
     */
    List<ProjectsFunction> getAllFunctionPage(Integer projectId, Integer offset,Integer pageSize,Integer functionStatus);

    /**
     * 通过项目id查询指派给我的的功能
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndRealizeUserId(Integer projectsId,Integer offset,Integer pageSize,Integer realizeUserId,Integer functionStatus);

    /**
     * 通过项目id查找项目中我发布的的全部功能点
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndPublishUserId(Integer projectsId,Integer offset,Integer pageSize,Integer publishUserId,Integer functionStatus);

    /**
     * 通过项目id查找项目中我参与的的全部功能点
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndUserId(Integer projectsId,Integer offset,Integer pageSize,Integer userId,Integer functionStatus);

    /**
     * 通过项目id查询指派给我的的功能（不分页）
     * @param projectsId
     * @param realizeUserId
     * @param functionStatus
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndRealizeUserId(Integer projectsId,Integer realizeUserId,Integer functionStatus);
}
