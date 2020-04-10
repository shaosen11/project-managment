package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserDuty;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsUserDutyMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsUserDutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:34 2020/4/10
 */
@Service
public class ProjectsUserDutyServiceImpl implements ProjectsUserDutyService {
    @Autowired
    ProjectsUserDutyMapper projectsUserDutyMapper;
    @Override
    public ProjectsUserDuty getById(Integer id) {
        return projectsUserDutyMapper.getById(id);
    }
}
