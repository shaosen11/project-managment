package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import cn.edu.lingnan.projectmanagment.bean.ProjectsFunction;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ProjectsFunctionMapper {
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
    Integer countByProjectIdAndStatus(@Param("id")Integer id,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id和status查询指派给我的功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectIdAndRealizeUserId(@Param("projectId")Integer projectId,@Param("userId")Integer userId,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id和status查询我发布的功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectIdAndPublishUserId(@Param("projectId")Integer projectId,@Param("userId")Integer userId,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id和status查询我参与的功能点数量
     * @return
     */
    Integer countProjectFunctionByProjectIdAndUserId(@Param("projectId")Integer projectId,@Param("userId")Integer userId,@Param("functionStatus")Integer functionStatus);

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
    List<ProjectsFunction> getAllFunctionPage(@Param("projectId")Integer projectId, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id查询指派给我的的功能
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndRealizeUserId(@Param("projectsId")Integer projectsId, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize,@Param("realizeUserId")Integer realizeUserId,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id查找项目中我发布的的全部功能点
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndPublishUserId(@Param("projectsId")Integer projectsId, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize,@Param("publishUserId")Integer publishUserId,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id查找项目中我参与的的全部功能点
     * @return
     */
    List<ProjectsFunction> getFunctionByProjectIdAndUserId(@Param("projectsId")Integer projectsId, @Param("offset")Integer offset, @Param("pageSize")Integer pageSize,@Param("userId")Integer userId,@Param("functionStatus")Integer functionStatus);

    /**
     * 通过项目id查询指派给我的的功能
     * @param projectsId
     * @param realizeUserId
     * @param functionStatus
     * @return
     */
    List<ProjectsFunction> getAllFunctionByProjectIdAndRealizeUserId(Integer projectsId,Integer realizeUserId,Integer functionStatus);
}

