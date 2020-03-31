package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.ProjectsPackage;
import cn.edu.lingnan.projectmanagment.bean.ProjectsPackageList;
import cn.edu.lingnan.projectmanagment.mapper.ProjectsPackageMapper;
import cn.edu.lingnan.projectmanagment.service.ProjectsPackageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:52 2020/3/28
 */
@Service
public class ProjectsPackageServiceImpl implements ProjectsPackageService {
    @Autowired
    ProjectsPackageMapper projectsPackageMapper;

    @Override
    public ProjectsPackage getById(Integer id) {
        return projectsPackageMapper.getById(id);
    }

    @Override
    public List<ProjectsPackage> getAllPackagesByProjects(Integer projectId) {
        return projectsPackageMapper.getAllPackagesByProjects(projectId);
    }

    @Override
    public List<ProjectsPackage> getAllDocumentsByProjectsAndPackage(Integer projectId, Integer packageId) {
        return projectsPackageMapper.getAllDocumentsByProjectsAndPackage(projectId, packageId);
    }

    @Override
    public ProjectsPackage getPackageIdByProjectId(Integer projectId) {
        return projectsPackageMapper.getPackageIdByProjectId(projectId);
    }

    @Override
    public ProjectsPackage getPackageByProjectIdAndPackageName(Integer projectId, String PackageName) {
        return projectsPackageMapper.getPackageByProjectIdAndPackageName(projectId, PackageName);
    }

    @Override
    public ProjectsPackage getDocumentsNameByProjectIdAndPackageNameAndDocumentsName(Integer projectId, String PackageName, String DocumentsName) {
        return projectsPackageMapper.getDocumentsNameByProjectIdAndPackageNameAndDocumentsName(projectId, PackageName, DocumentsName);
    }

    @Override
    public boolean delete(Integer id) {
        return projectsPackageMapper.delete(id);
    }

    @Override
    public boolean update(ProjectsPackage bean) {
        return projectsPackageMapper.update(bean);
    }

    @Override
    public boolean insert(ProjectsPackage bean) {
        return projectsPackageMapper.insert(bean);
    }

    @Override
    public ProjectsPackage getDocumentsNameByProjectIdAndDocumentsName(Integer projectId, String documentsName) {
        return projectsPackageMapper.getDocumentsNameByProjectIdAndDocumentsName(projectId, documentsName);
    }

    @Override
    public List<ProjectsPackageList> getAllPackagesListByProjectId(Integer projectId) {
        return projectsPackageMapper.getAllPackagesListByProjectId(projectId);
    }
}
