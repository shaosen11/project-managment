package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserCooperation;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsUserCooperationMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsUserCooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:07 2020/4/27
 */
@Service
public class ProjectsUserCooperationServiceImpl implements ProjectsUserCooperationService {
    @Autowired
    ProjectsUserCooperationMapper projectsUserCooperationMapper;

    @Override
    public ProjectsUserCooperation getByProjectIdAndNotInProjectUserId(Integer projectId, Integer notInProjectUserId) {
        return projectsUserCooperationMapper.getByProjectIdAndNotInProjectUserId(projectId, notInProjectUserId);
    }

    @Override
    public boolean insert(ProjectsUserCooperation projectsUserCooperation) {
        return projectsUserCooperationMapper.insert(projectsUserCooperation);
    }

    @Override
    public boolean update(ProjectsUserCooperation projectsUserCooperation) {
        return projectsUserCooperationMapper.update(projectsUserCooperation);
    }
}
