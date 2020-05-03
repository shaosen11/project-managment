package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserCooperation;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsUserCooperationMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsUserCooperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public ProjectsUserCooperation getByProjectIdAndInProjectUserIdAndNotInProjectUserIdAndInviteAndFinish(Integer projectId, Integer inProjectUserId, Integer notInProjectUserId, Integer inviteFlag, Integer finishFlag) {
        return projectsUserCooperationMapper.getByProjectIdAndInProjectUserIdAndNotInProjectUserIdAndInviteAndFinish(projectId, inProjectUserId, notInProjectUserId, inviteFlag, finishFlag);
    }

    @Override
    public void insert(ProjectsUserCooperation projectsUserCooperation) {
        projectsUserCooperationMapper.insert(projectsUserCooperation);
    }

    @Override
    public boolean update(ProjectsUserCooperation projectsUserCooperation) {
        return projectsUserCooperationMapper.update(projectsUserCooperation);
    }

    @Override
    public ProjectsUserCooperation getById(Integer id) {
        return projectsUserCooperationMapper.getById(id);
    }

    @Override
    public List<ProjectsUserCooperation> getByProjectIdAndNotInProjectUserIdAndInviteAndFinish(Integer projectId, Integer notInProjectUserId, Integer invite, Integer finish) {
        return projectsUserCooperationMapper.getByProjectIdAndNotInProjectUserIdAndInviteAndFinish(projectId, notInProjectUserId, invite, finish);
    }
}
