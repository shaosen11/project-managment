package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.ProjectsRecommendation;
import cn.edu.lingnan.projectmanagment.mapper.ProjectMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectsService {
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public Projects getById(Integer id) {
        return projectMapper.getById(id);
    }

    @Override
    public Projects getByIdAndNoDel(Integer id) {
        return projectMapper.getByIdAndNoDel(id);
    }

    @Override
    public List<Projects> getProjectList() {
        return projectMapper.getProjectList();
    }

    @Override
    public List<Projects> getProjectListByUserId(Integer userId) {
        return projectMapper.getProjectListByUserId(userId);
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
    public boolean updateSchedule(Projects project) {
        return projectMapper.updateSchedule(project);
    }

    @Override
    public boolean reductionProject(Integer id) {
        return projectMapper.reductionProject(id);
    }

    @Override
    public List<Projects> getDelProjectList() {
        return projectMapper.getDelProjectList();
    }

    @Override
    public Projects getAdminByUserIdAndProjectId(Integer userId, Integer projectId) {
        return projectMapper.getAdminByUserIdAndProjectId(userId, projectId);
    }

    @Override
    public List<ProjectsRecommendation> getProject(Integer pageNum, Integer pageSize) {
        return projectMapper.getProject(pageNum,pageSize);
    }

    @Override
    public boolean updateProjectClickNumber(Integer projectId) {
        return projectMapper.updateProjectClickNumber(projectId);
    }

    @Override
    public List<Projects> getTodayProject() {
        return projectMapper.getTodayProject();
    }

    @Override
    public List<Projects> getWeekProject() {
        return projectMapper.getWeekProject();
    }

    @Override
    public List<ProjectsRecommendation> getRecommendedCommodities(Integer projectId) {
        return projectMapper.getRecommendedCommodities(projectId);
    }

    @Override
    public Integer countProjectsRecommendation() {
        return projectMapper.countProjectsRecommendation();
    }

    @Override
    public List<ProjectsRecommendation> getProjectsByType(Integer pageNum, Integer pageSize, String type) {
        return projectMapper.getProjectsByType(pageNum,pageSize,type);
    }

    @Override
    public Integer countProjectsNumberByType(String type) {
        return projectMapper.countProjectsNumberByType(type);
    }

}
