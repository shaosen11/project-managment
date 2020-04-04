package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.Documents;
import cn.edu.lingnan.projectmanagment.mapper.DocumentsMapper;
import cn.edu.lingnan.projectmanagment.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:20 2020/3/31
 */
@Service
public class DocumentsServiceImpl implements DocumentsService {

    @Autowired
    private DocumentsMapper documentMapper;

    @Override
    public Documents getById(Integer id) {
        return documentMapper.getById(id);
    }

    @Override
    public Documents getByProjectsIdAndVersionAndName(Integer projectsId, Integer version, String name) {
        return documentMapper.getByProjectsIdAndVersionAndName(projectsId, version, name);
    }

    @Override
    public Documents getByProjectsIdAndVersionFlagAndName(Integer projectsId, Integer versionflag, String name) {
        return documentMapper.getByProjectsIdAndVersionFlagAndName(projectsId, versionflag, name);
    }

    @Override
    public Integer getVersionByProjectsIdAndName(Integer projectId, String name) {
        return documentMapper.getVersionByProjectsIdAndName(projectId, name);
    }

    @Override
    public List<Documents> getAllByProjectId(Integer projectId) {
        return documentMapper.getAllByProjectId(projectId);
    }

    @Override
    public boolean insert(Documents bean) {
        return documentMapper.insert(bean);
    }

    @Override
    public boolean update(Documents bean) {
        return documentMapper.update(bean);
    }

    @Override
    public boolean delete(Integer id) {
        return documentMapper.delete(id);
    }

    public List<Documents> getAllDeleteDocuments() {
        return documentMapper.getAllDeleteDocuments();
    }

    @Override
    public boolean undo(Integer id) {
        return documentMapper.undo(id);
    }
}
