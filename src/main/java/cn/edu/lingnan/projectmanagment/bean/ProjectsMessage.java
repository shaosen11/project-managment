package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:44 2020/4/17
 */
@Data
public class ProjectsMessage {
    private Integer id;
    private Integer projectId;
    private Integer fromUserId;
    private Integer toUserId;
    private Integer typeId;
    private ProjectsMessageType projectsMessageType;
    private String message;
    private String time;
    private Integer isRead;
    private Integer deleteFlag;
}

