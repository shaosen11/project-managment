package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsPackage;
import cn.edu.lingnan.projectmanagment.bean.ProjectsPackageList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:50 2020/3/28
 */
public interface ProjectsPackageService {
    ProjectsPackage getById(Integer id);

    List<ProjectsPackage> getAllPackagesByProjects(Integer projectId);

    List<ProjectsPackage> getAllDocumentsByProjectsAndPackage(Integer projectId, Integer packageId);

    ProjectsPackage getPackageIdByProjectId(Integer projectId);

    ProjectsPackage getPackageByProjectIdAndPackageName(Integer projectId, String PackageName);

    ProjectsPackage getDocumentsNameByProjectIdAndPackageNameAndDocumentsName(@Param("projectId") Integer projectId, @Param("PackageName") String PackageName, @Param("DocumentsName") String DocumentsName);

    boolean delete(Integer id);

    boolean update(ProjectsPackage bean);

    boolean insert(ProjectsPackage bean);

    ProjectsPackage getDocumentsNameByProjectIdAndDocumentsName(Integer projectId, String documentsName);

    List<ProjectsPackageList> getAllPackagesListByProjectId(Integer projectId);
}

