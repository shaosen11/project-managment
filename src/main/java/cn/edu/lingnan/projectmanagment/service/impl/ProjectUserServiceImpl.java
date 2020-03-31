package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import cn.edu.lingnan.projectmanagment.mapper.ProjectUserMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectUserServiceImpl implements ProjectUserService {
    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public List<ProjectsUser> getProjectUserList() {
        return projectUserMapper.getProjectUserList();
    }

    @Override
    public boolean addProjectUser(ProjectsUser projectsUser) {
        return projectUserMapper.addProjectUser(projectsUser);
    }

    @Override
    public boolean deleteProjectUser(Integer id) {
        return projectUserMapper.deleteProjectUser(id);
    }

    @Override
    public boolean deleteProjectUserByProjectsId(Integer id) {
        return projectUserMapper.deleteProjectUserByProjectsId(id);
    }

    @Override
    public boolean editProjectUser(ProjectsUser projectsUser) {
        return projectUserMapper.editProjectUser(projectsUser);
    }

    @Override
    public boolean reductionProjectUser(Integer id) {
        return projectUserMapper.reductionProjectUser(id);
    }

    @Override
    public List<ProjectsUser> getDelProjectUserList() {
        return projectUserMapper.getDelProjectUserList();
    }
}
