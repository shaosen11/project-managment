package cn.edu.lingnan.projectmanagment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author shaosen
 */
@Mapper
@Repository
public interface MyRBACServiceMapper {
    /**
     * 通过用户邮箱查询用户权限
     * @param email
     * @return
     */
    List<String> findUrlsByEmail(@Param("email") String email);

}

