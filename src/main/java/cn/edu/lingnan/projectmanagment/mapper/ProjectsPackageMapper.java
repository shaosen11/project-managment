package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsPackage;
import cn.edu.lingnan.projectmanagment.bean.ProjectsPackageList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:53 2020/3/28
 */
@Mapper
@Repository
public interface ProjectsPackageMapper {
     ProjectsPackage getById(Integer id);

     List<ProjectsPackage> getAllPackagesByProjects(Integer projectId);

     List<ProjectsPackage> getAllDocumentsByProjectsAndPackage(@Param("projectId") Integer projectId, @Param("packageId") Integer packageId);

     ProjectsPackage getPackageIdByProjectId(Integer projectId);

     ProjectsPackage getPackageByProjectIdAndPackageName(@Param("projectId") Integer projectId, @Param("PackageName") String PackageName);

     ProjectsPackage getDocumentsNameByProjectIdAndPackageNameAndDocumentsName(@Param("projectId") Integer projectId, @Param("PackageName") String PackageName, @Param("DocumentsName") String DocumentsName);

     boolean delete(Integer id);

     boolean update(ProjectsPackage bean);

     boolean insert(ProjectsPackage bean);

     ProjectsPackage getDocumentsNameByProjectIdAndDocumentsName(@Param("projectId") Integer projectId, @Param("documentsName") String documentsName);

     List<ProjectsPackageList> getAllPackagesListByProjectId(Integer projectId);
}
