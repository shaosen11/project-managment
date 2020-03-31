package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.Documents;
import cn.edu.lingnan.projectmanagment.service.DocumentsService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:20 2020/3/31
 */
@Service
public class DocumentsServiceImpl implements DocumentsService {

    @Override
    public Documents getById(Integer id) {
        return null;
    }

    @Override
    public Documents getByVersionAndName(Integer version, String name) {
        return null;
    }

    @Override
    public Documents getByVersionFlagAndName(Integer versionflag, String name) {
        return null;
    }

    @Override
    public List<Documents> getAllByProjectId(Integer projectId) {
        return null;
    }

    @Override
    public Integer getVersionByName(String name) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean update(Documents bean) {
        return false;
    }

    @Override
    public boolean insert(Documents bean) {
        return false;
    }

    @Override
    public List<Documents> getAllDeleteDocuments() {
        return null;
    }

    @Override
    public boolean undo(Integer id) {
        return false;
    }
}
