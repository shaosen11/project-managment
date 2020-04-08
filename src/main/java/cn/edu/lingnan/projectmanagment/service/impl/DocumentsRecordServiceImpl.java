package cn.edu.lingnan.projectmanagment.service.impl;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import cn.edu.lingnan.projectmanagment.mapper.DocumentsRecordMapper;
import cn.edu.lingnan.projectmanagment.service.DocumentsRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:59 2020/3/28
 */
@Service
public class DocumentsRecordServiceImpl implements DocumentsRecordService {
    @Autowired
    private DocumentsRecordMapper documentsRecordMapper;

    @Override
    public DocumentsRecord getById(Integer id) {
        return documentsRecordMapper.getById(id);
    }

    @Override
    public List<DocumentsRecord> getAllByProjectId(Integer projectsId) {
        return documentsRecordMapper.getAllByProjectId(projectsId);
    }

    @Override
    public boolean delete(Integer id) {
        return documentsRecordMapper.delete(id);
    }

    @Override
    public boolean update(DocumentsRecord bean) {
        return documentsRecordMapper.update(bean);
    }

    @Override
    public boolean insert(DocumentsRecord bean) {
        return documentsRecordMapper.insert(bean);
    }

    @Override
    public List<DocumentsRecord> getAllDeleteDocumentsRecord() {
        return documentsRecordMapper.getAllDeleteDocumentsRecord();
    }

    @Override
    public boolean undo(Integer id) {
        return documentsRecordMapper.undo(id);
    }

    @Override
    public Integer count(Integer projectId) {
        return documentsRecordMapper.count(projectId);
    }

    @Override
    public List<DocumentsRecord> getPage(Integer projectId, Integer offset, Integer pageSize) {
        return documentsRecordMapper.getPage(projectId, offset, pageSize);
    }
}
