package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.MessageType;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:33 2020/4/11
 */
public interface MessageTypeService {
    /**
     * 通过消息码得到消息类型
     * @param id
     * @return
     */
    MessageType getById(Integer id);
}
