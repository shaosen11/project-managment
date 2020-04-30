package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:26 2020/4/11
 */
@Data
public class Message {
    private Integer id;
    private Integer typeId;
    private MessageType messageType;
    private Integer fromUserId;
    private Integer toUserId;
    private String message;
    private String time;
    private Integer isRead;
    private Integer deleteFlag;
    private Integer needToDo;
}

