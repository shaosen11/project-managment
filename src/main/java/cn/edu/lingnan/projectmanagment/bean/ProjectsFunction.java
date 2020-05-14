package cn.edu.lingnan.projectmanagment.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProjectsFunction {
    private Integer id;
    private Integer projectsId;
    private String projectName;
    private Integer functionId;
    private String functionName;
    private Integer functionStatus;
    private Integer publishUserId;
    private String publishUserName;
    private Integer realizeUserId;
    private String realizeUserName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedStartTime;//计划开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date plannedEndTime;//计划结束时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actualStartTime;////实际开始时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date actualEndTime;//实际结束时间
    private String delReason;
    private Integer deleteFlag;
}

