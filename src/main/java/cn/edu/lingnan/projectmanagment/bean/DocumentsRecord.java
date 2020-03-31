package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:54 2020/3/28
 */
@Data
public class DocumentsRecord {
    private Integer id;
    private Integer projectId;
    private Integer userId;
    private Date operateTime;
    private String ip;
    private String operateMessage;
}
