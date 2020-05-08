package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 16:53 2020/5/5
 */
@Data
public class ProjectsMessageNeedToDoRelationship {
    private Integer id;
    private Integer projectMessageId;
    private Integer documentId;
    private Integer deleteFlag;
}
