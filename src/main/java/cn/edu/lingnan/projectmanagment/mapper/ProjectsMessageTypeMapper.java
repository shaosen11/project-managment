package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessageType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:54 2020/4/17
 */
@Mapper
@Repository
public interface ProjectsMessageTypeMapper {
    /**
     * 通过消息码得到消息类型
     * @param id
     * @return
     */
    ProjectsMessageType getById(Integer id);
}

