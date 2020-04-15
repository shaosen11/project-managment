package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 1:41 2020/4/16
 */
@Data
public class UserCodeUpdateRecord {
    private Integer userId;
    private Date time;
    private Integer codeUpdateCount;
}
