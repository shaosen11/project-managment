package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsUser;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsUserMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 15:46 2020/3/28
 */
@Service
public class ProjectsUserServiceImpl implements ProjectsUserService {
    @Autowired
    ProjectsUserMapper projectsUserMapper;

    @Override
    public List<ProjectsUser> getProjectsByUserId(Integer userId) {
        return projectsUserMapper.getProjectsByUserId(userId);
    }

    @Override
    public List<ProjectsUser> getUserByProjectId(Integer projectId) {
        return projectsUserMapper.getUserByProjectId(projectId);
    }
}
