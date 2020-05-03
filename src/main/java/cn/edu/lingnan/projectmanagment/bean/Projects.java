package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Projects {
    private Integer id;
    private String name;
    private Integer chargeUserId;
    private Integer codeLineNumber;
    private Integer codeUpdateCount;
    private String schedule;
    private Integer functionPoints;
    private Integer completedFunctionPoints;
    private Date lastUpdateTime;
    private String type;
    private Integer storeCount;
    private Integer likeCount;
    private Integer clickCount;
    private String characterization;
    private Date createTime;
    private Integer enabled;
    private Integer userCount;
}

