package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Documents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:21 2020/3/31
 */
@Mapper
@Repository
public interface DocumentsMapper {
    Documents getById(Integer id);

    Documents getByVersionAndName(@Param("version") Integer version, @Param("name") String name);

    Documents getByVersionFlagAndName(@Param("versionflag") Integer versionflag, @Param("name") String name);

    Integer getVersionByName(String name);

    List<Documents> getAllByProjectId(Integer projectId);

    boolean delete(Integer id);

    boolean update(Documents bean);

    boolean insert(Documents bean);

    List<Documents> getAllDeleteDocuments();

    boolean undo(Integer id);
}
