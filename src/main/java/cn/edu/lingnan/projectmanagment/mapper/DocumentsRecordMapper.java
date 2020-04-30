package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DocumentsRecordMapper {
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

