package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsCodeLine;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsCodeLineMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsCodeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 19:57 2020/4/3
 */
@Service
public class ProjectsCodeLineServiceImpl implements ProjectsCodeLineService {
    @Autowired
    ProjectsCodeLineMapper projectsCodeLineMapper;


    @Override
    public ProjectsCodeLine getProjectsCodeLineByProjectIdAndToday(Integer projectsId) {
        return projectsCodeLineMapper.getProjectsCodeLineByProjectIdAndToday(projectsId);
    }

    @Override
    public ProjectsCodeLine getProjectsCodeLineByProjectIdAndLastDay(Integer projectsId) {
        return projectsCodeLineMapper.getProjectsCodeLineByProjectIdAndLastDay(projectsId);
    }

    @Override
    public boolean insert(ProjectsCodeLine projectsCodeLine) {
        return projectsCodeLineMapper.insert(projectsCodeLine);
    }

    @Override
    public boolean update(ProjectsCodeLine projectsCodeLine) {
        return projectsCodeLineMapper.update(projectsCodeLine);
    }

    @Override
    public List<ProjectsCodeLine> getAllProjectsCodeLineByProjectId(Integer projectId) {
        return projectsCodeLineMapper.getAllProjectsCodeLineByProjectId(projectId);
    }
}
