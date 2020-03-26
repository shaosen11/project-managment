package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 17:50 2020/3/24
 */
@Mapper
@Repository
public interface UserRoleMapper {
    /**
     * 插入用户信息
     * @param userRole
     * @return
     */
    boolean insertUserRole(UserRole userRole);
}
