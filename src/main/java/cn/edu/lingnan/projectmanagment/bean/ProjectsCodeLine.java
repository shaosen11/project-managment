package cn.edu.lingnan.projectmanagment.bean;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.util.Date;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 19:48 2020/4/3
 */
@Data
public class ProjectsCodeLine {
    private Integer id;
    private Integer projectsId;
    private Integer codeLineNumber;
    private Date uploadTime;
    private String uploadTimeString;
}

