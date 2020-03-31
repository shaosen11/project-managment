package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

@Data
public class Myprojects {
    private Integer projectId;
    private String projectName;
    private Integer chargeUserId;
    private String chargeUser;
    private String schedule;
    private Integer functionPoints;
    private Integer completedFunctionPoints;
    private Integer updateCount;
    private Integer myUpdateCount;
    private String type;
}
