package cn.edu.lingnan.projectmanagment.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MyRBACServiceMapper {
    List<String> findUrlsByEmail(@Param("email") String email);

}
