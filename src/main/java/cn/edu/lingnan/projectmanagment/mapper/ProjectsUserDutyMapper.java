package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserDuty;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:36 2020/4/10
 */
@Mapper
@Repository
public interface ProjectsUserDutyMapper {
    /**
     * 查询项目中的职责
     * @param id
     * @return
     */
    ProjectsUserDuty getById(Integer id);
}
