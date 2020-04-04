package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.UserLike;
import org.apache.ibatis.annotations.Param;

public interface UserLikeService {
    /**
     * 查询一条项目信息
     * @return
     */
    UserLike getOneUserLike(@Param("userId")Integer userId, @Param("projectId")Integer projectId);

    /**
     * 添加收藏记录
     * @param userLike
     * @return
     */
    boolean addUserLike(UserLike userLike);

    /**
     * 删除收藏记录
     * @return
     */
    boolean deleteUserLike(@Param("userId")Integer userId, @Param("projectId")Integer projectId);

    /**
     * 还原收藏记录
     * @return
     */
    boolean reductionUserLike(@Param("userId")Integer userId, @Param("projectId")Integer projectId);

    /**
     * 查询项目被收藏数
     * @return
     */
    Integer countProjectBeLiked(Integer id);
}
