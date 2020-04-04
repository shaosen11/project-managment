package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.Projects;
import cn.edu.lingnan.projectmanagment.bean.UserStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserStoreMapper {
    /**
     * 查询一条项目信息
     * @return
     */
    UserStore getOneUserStore(@Param("userId")Integer userId, @Param("projectId")Integer projectId);

    /**
     * 添加收藏记录
     * @param userStore
     * @return
     */
    boolean addUserStore(UserStore userStore);

    /**
     * 删除收藏记录
     * @return
     */
    boolean deleteUserStore(@Param("userId")Integer userId, @Param("projectId")Integer projectId);

    /**
     * 还原收藏记录
     * @return
     */
    boolean reductionUserStore(@Param("userId")Integer userId, @Param("projectId")Integer projectId);

    /**
     * 查询项目被收藏数
     * @return
     */
    Integer countProjectBeStored(Integer id);
}
