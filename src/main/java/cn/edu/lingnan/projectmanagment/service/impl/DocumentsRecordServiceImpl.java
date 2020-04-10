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
    public Integer getDocumentsRecordCountByProjectId(Integer projectId) {
        return documentsRecordMapper.getDocumentsRecordCountByProjectId(projectId);
    }
    @Override
    public Integer getDocumentsRecordCountByProjectIdAndUserId(Integer projectId, Integer userId) {
        return documentsRecordMapper.getDocumentsRecordCountByProjectIdAndUserId(projectId, userId);
    }

    @Override
    public List<DocumentsRecord> getDocumentsRecordPageByProjectId(Integer projectId, Integer offset, Integer pageSize) {
        return documentsRecordMapper.getDocumentsRecordPageByProjectId(projectId, offset, pageSize);
    }

    @Override
    public List<DocumentsRecord> getDocumentsRecordPageByProjectIdAndUserId(Integer projectId, Integer userId,Integer offset, Integer pageSize) {
        return documentsRecordMapper.getDocumentsRecordPageByProjectIdAndUserId(projectId, userId, offset, pageSize);
    }
}
