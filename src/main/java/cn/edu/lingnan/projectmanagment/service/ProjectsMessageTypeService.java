package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsMessageType;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:51 2020/4/17
 */
public interface ProjectsMessageTypeService {
    /**
     * 通过消息码得到消息类型
     * @param id
     * @return
     */
    ProjectsMessageType getById(Integer id);
}
