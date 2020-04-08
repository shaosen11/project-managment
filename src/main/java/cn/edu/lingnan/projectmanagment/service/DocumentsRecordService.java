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

    Integer count(Integer projectId);

    List<DocumentsRecord> getPage(Integer projectId, Integer offset, Integer pageSize);
}
