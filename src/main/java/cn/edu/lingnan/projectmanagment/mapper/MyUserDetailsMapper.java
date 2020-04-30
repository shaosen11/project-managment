package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author shaosen
 */
@Mapper
@Repository
public interface MyUserDetailsMapper {

    /**
     * 根据userID查询用户信息
     * @param email
     * @return
     */
    MyUserDetails findByEmail(@Param("email") String email);


    /**
     * 根据userID查询你用户角色
     * @param email
     * @return
     */
    List<String> findRoleByEmail(@Param("email") String email);


    /**
     * 根据用户角色查询用户权限
     * @param roleCodes
     * @return
     */
    List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}

