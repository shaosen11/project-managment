package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.mapper.ProjectMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Projects getById(Integer id) {
        return projectMapper.getById(id);
    }

    @Override
    public List<Projects> getProjectList() {
        return projectMapper.getProjectList();
    }

    @Override
    public boolean addProject(Projects project) {
        return projectMapper.addProject(project);
    }

    @Override
    public boolean deleteProject(Integer id) {
        return projectMapper.deleteProject(id);
    }

    @Override
    public boolean editProject(Projects project) {
        return projectMapper.editProject(project);
    }

    @Override
    public boolean reductionProject(Integer id) {
        return projectMapper.reductionProject(id);
    }

    @Override
    public List<Projects> getDelProjectList() {
        return projectMapper.getDelProjectList();
    }
}
