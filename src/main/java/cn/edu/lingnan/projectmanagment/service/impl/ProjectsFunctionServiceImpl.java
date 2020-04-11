package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsFunction;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsFunctionMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsFunctionServiceImpl implements ProjectsFunctionService {
    @Autowired
    private ProjectsFunctionMapper projectsFunctionMapper;

    @Override
    public List<ProjectsFunction> getProjectFunctionList() {
        return projectsFunctionMapper.getProjectFunctionList();
    }

    @Override
    public ProjectsFunction getOneProjectFunction(Integer id) {
        return projectsFunctionMapper.getOneProjectFunction(id);
    }

    @Override
    public ProjectsFunction getById(Integer id) {
        return projectsFunctionMapper.getById(id);
    }

    @Override
    public Integer countProjectFunctionByProjectId(Integer id) {
        return projectsFunctionMapper.countProjectFunctionByProjectId(id);
    }

    @Override
    public Integer countByProjectIdAndStatus(Integer id, Integer functionStatus) {
        return projectsFunctionMapper.countByProjectIdAndStatus(id,functionStatus);
    }

    @Override
    public Integer countProjectFunctionByProjectIdAndRealizeUserId(Integer projectId, Integer userId, Integer functionStatus) {
        return projectsFunctionMapper.countProjectFunctionByProjectIdAndRealizeUserId(projectId,userId,functionStatus);
    }

    @Override
    public Integer countProjectFunctionByProjectIdAndPublishUserId(Integer projectId, Integer userId, Integer functionStatus) {
        return projectsFunctionMapper.countProjectFunctionByProjectIdAndPublishUserId(projectId,userId,functionStatus);
    }

    @Override
    public Integer countProjectFunctionByProjectIdAndUserId(Integer projectId, Integer userId, Integer functionStatus) {
        return projectsFunctionMapper.countProjectFunctionByProjectIdAndUserId(projectId,userId,functionStatus);
    }


    @Override
    public List<ProjectsFunction> getAllFunctionByProjectId(Integer id) {
        return projectsFunctionMapper.getAllFunctionByProjectId(id);
    }

    @Override
    public Integer findMaxFunctionId(Integer id) {
        return projectsFunctionMapper.findMaxFunctionId(id);
    }

    @Override
    public List<ProjectsFunction> getAllFunctionPage(Integer projectId, Integer offset, Integer pageSize, Integer functionStatus) {
        return projectsFunctionMapper.getAllFunctionPage(projectId,offset,pageSize,functionStatus);
    }

    @Override
    public List<ProjectsFunction> getFunctionByProjectIdAndRealizeUserId(Integer projectsId, Integer offset, Integer pageSize, Integer realizeUserId, Integer functionStatus) {
        return projectsFunctionMapper.getFunctionByProjectIdAndRealizeUserId(projectsId,offset,pageSize,realizeUserId,functionStatus);
    }

    @Override
    public List<ProjectsFunction> getFunctionByProjectIdAndPublishUserId(Integer projectsId, Integer offset, Integer pageSize, Integer publishUserId, Integer functionStatus) {
        return projectsFunctionMapper.getFunctionByProjectIdAndPublishUserId(projectsId,offset,pageSize,publishUserId,functionStatus);
    }

    @Override
    public List<ProjectsFunction> getFunctionByProjectIdAndUserId(Integer projectsId, Integer offset, Integer pageSize, Integer userId, Integer functionStatus) {
        return projectsFunctionMapper.getFunctionByProjectIdAndUserId(projectsId,offset,pageSize,userId,functionStatus);
    }


    @Override
    public boolean addProjectFunction(ProjectsFunction projectsFunction) {
        return projectsFunctionMapper.addProjectFunction(projectsFunction);
    }

    @Override
    public boolean deleteProjectFunction(Integer id) {
        return projectsFunctionMapper.deleteProjectFunction(id);
    }

    @Override
    public boolean deleteProjectFunctionByProjectsId(Integer id) {
        return projectsFunctionMapper.deleteProjectFunctionByProjectsId(id);
    }

    @Override
    public boolean editProjectFunction(ProjectsFunction projectsFunction) {
        return projectsFunctionMapper.editProjectFunction(projectsFunction);
    }

    @Override
    public boolean reductionProjectFunction(Integer id) {
        return projectsFunctionMapper.reductionProjectFunction(id);
    }

    @Override
    public List<ProjectsFunction> getDelProjectFunctionList() {
        return projectsFunctionMapper.getDelProjectFunctionList();
    }
}
