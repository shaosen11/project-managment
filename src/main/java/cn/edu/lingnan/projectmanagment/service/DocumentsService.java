package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.Documents;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:19 2020/3/31
 */
public interface DocumentsService {
    Documents getById(Integer id);

    Documents getByProjectsIdAndVersionAndName(Integer projectsId, Integer version, String name);

    Documents getByVersionFlagAndName(Integer versionflag, String name);

    List<Documents> getAllByProjectId(Integer projectId);

    Integer getVersionByName(String name);

    boolean delete(Integer id);

    boolean update(Documents bean);

    boolean insert(Documents bean);

    List<Documents> getAllDeleteDocuments();

    boolean undo(Integer id);
}
