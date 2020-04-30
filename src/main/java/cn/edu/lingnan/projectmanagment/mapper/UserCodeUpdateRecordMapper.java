package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.UserCodeUpdateRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 1:46 2020/4/16
 */
@Mapper
@Repository
public interface UserCodeUpdateRecordMapper {
    /**
     * 查找用户所有贡献图
     * @param userId
     * @return
     */
    List<UserCodeUpdateRecord> getAllByUserId(Integer userId);
}

