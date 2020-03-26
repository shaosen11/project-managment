package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Projects {
    private Integer id;
    private String name;
    private Integer chargeUserId;
    private Integer codeLineNumber;
    private String schedule;
    private Integer functionPoints;
    private Integer completedFunctionPoints;
    private Integer updateCount;
    private String type;
    private String characterization;
    private Date createTime;
    private Integer enabled;

}
