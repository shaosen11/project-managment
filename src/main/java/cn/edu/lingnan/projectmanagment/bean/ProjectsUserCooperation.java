package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:00 2020/4/27
 */
@Data
public class ProjectsUserCooperation {
    private Integer id;
    private Integer projectsId;
    private Integer inProjectUserId;
    private Integer notInProjectUserId;
    private Integer dutyCode;
    private Date time;
    private Integer deleteFlag;
    private Integer inviteFlag;
    private Integer joinFlag;
    private Integer finishFlag;
}
