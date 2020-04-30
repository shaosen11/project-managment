package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:58 2020/3/28
 */
public interface DocumentsRecordService {
    DocumentsRecord getById(Integer id);

    List<DocumentsRecord> getAllByProjectId(Integer projectsId);

    boolean delete(Integer id);

    boolean update(DocumentsRecord bean);

    boolean insert(DocumentsRecord bean);

    List<DocumentsRecord> getAllDeleteDocumentsRecord();

    boolean undo(Integer id);

    Integer getDocumentsRecordCountByProjectId(Integer projectId);

    Integer getDocumentsRecordCountByProjectIdAndUserId(Integer projectId, Integer userId);

    List<DocumentsRecord> getDocumentsRecordPageByProjectId(Integer projectId, Integer offset, Integer pageSize);

    List<DocumentsRecord> getDocumentsRecordPageByProjectIdAndUserId(Integer projectId, Integer userId, Integer offset, Integer pageSize);
}

