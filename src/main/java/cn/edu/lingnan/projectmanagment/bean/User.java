package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String duty;
    private Date hrieDate;
    private Date lastLoginDate;
    private Integer deleteFlag;
}
