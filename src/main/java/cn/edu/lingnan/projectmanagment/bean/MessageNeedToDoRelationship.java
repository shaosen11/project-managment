package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:11 2020/4/30
 */
@Data
public class MessageNeedToDoRelationship {
    Integer Id;
    Integer messageId;
    Integer projectsUserCooperrationId;
}
