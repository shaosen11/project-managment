package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 12:20 2020/3/22
 */
@Data
public class UserRecord implements Serializable {
    private Integer id;
    private Integer userId;
    private Date operateTime;
    private String operateMassage;
    private String ip;
}
