package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

@Data
public class UserLike {
    private Integer id;
    private Integer userId;
    private Integer projectsId;
    private Integer deleteFlag;
}
