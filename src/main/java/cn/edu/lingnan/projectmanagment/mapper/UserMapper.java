package cn.edu.lingnan.projectmanagment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    boolean ckeckUsername(@Param("username") String username);
}

