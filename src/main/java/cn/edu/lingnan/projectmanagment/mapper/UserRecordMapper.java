package cn.edu.lingnan.projectmanagment.mapper;

import cn.edu.lingnan.projectmanagment.bean.UserRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 12:31 2020/3/22
 */
@Mapper
@Repository
public interface UserRecordMapper {
    boolean insert(UserRecord userRecord);
}

