package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessageType;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsMessageTypeMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsMessageTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:51 2020/4/17
 */
@Service
public class ProjectsMessageTypeServiceImpl implements ProjectsMessageTypeService {
    @Autowired
    ProjectsMessageTypeMapper projectsMessageTypeMapper;
    @Override
    public ProjectsMessageType getById(Integer id) {
        return projectsMessageTypeMapper.getById(id);
    }
}
