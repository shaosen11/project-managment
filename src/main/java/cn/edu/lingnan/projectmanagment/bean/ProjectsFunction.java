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
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date plannedStartTime;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date plannedEndTime;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date actualStartTime;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private Date actualEndTime;
    private String delReason;
    private Integer deleteFlag;
    private String description;
}

