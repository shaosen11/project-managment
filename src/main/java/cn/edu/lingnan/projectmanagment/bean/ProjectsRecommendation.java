package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ProjectsRecommendation {
    private Integer projectId;
    private Integer userId;
    private String name;
    private String username;
    private String firstname;
    private String type;
    private String characterization;
    private Integer storeCount;
    private Integer clickCount;
    private Integer storeFlag;
    private Integer updateTime;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updateDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createDate;

}
