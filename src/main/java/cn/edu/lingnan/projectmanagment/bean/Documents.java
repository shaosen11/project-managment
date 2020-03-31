package cn.edu.lingnan.projectmanagment.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 23:46 2020/3/28
 */
@Data
public class Documents {
    private Integer id;
    private String name;
    private Integer userId;
    private Integer version;
    private String versionMessage;
    private String serialNumber;
    private Integer projectId;
    private Date uploadTime;
    private Integer versionFlag;
    private Integer codeLineNumber;
}
