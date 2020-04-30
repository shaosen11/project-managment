package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.UserClick;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserClickMapper {
    /**
     * 查询一条点击记录
     * @return
     */
    UserClick getOneUserClick(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    /**
     * 添加点击记录
     * @param serClick
     * @return
     */
    boolean addUserClick(UserClick userClick);

    /**
     * 删除点击记录
     * @return
     */
    boolean deleteUserClick(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    /**
     * 还原点击记录
     * @return
     */
    boolean reductionUserClick(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    /**
     * 查询项目点击次数
     * @return
     */
    Integer countProjectByClick(Integer id);
}

