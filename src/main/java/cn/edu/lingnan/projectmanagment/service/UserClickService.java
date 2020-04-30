package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.UserClick;
import org.apache.ibatis.annotations.Param;

public interface UserClickService {
    /**
     * 查询一条点击记录
     * @return
     */
    UserClick getOneUserClick(@Param("userId") Integer userId, @Param("projectId") Integer projectId);

    /**
     * 添加点击记录
     * @param userClick
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
