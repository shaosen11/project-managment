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
    private Date plannedStartTime;//计划开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedEndTime;//计划结束时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actualStartTime;////实际开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actualEndTime;//实际结束时间
}

