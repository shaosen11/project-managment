package cn.edu.lingnan.projectmanagment.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer documentCount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //计划开始时间
    private Date plannedStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //计划结束时间
    private Date plannedEndTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    ////实际开始时间
    private Date actualStartTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    //实际结束时间
    private Date actualEndTime;
}

