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
    public Integer countProjectFunctionByProjectId(Integer id) {
        return projectsFunctionMapper.countProjectFunctionByProjectId(id);
    }

    @Override
    public Integer countCompletedProjectFunctionByProjectId(Integer id) {
        return projectsFunctionMapper.countCompletedProjectFunctionByProjectId(id);
    }

    @Override
    public Integer findMaxFunctionId(Integer id) {
        return projectsFunctionMapper.findMaxFunctionId(id);
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
