package cn.edu.lingnan.projectmanagment.service;

import cn.edu.lingnan.projectmanagment.bean.ProjectsUserDuty;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 22:34 2020/4/10
 */
public interface ProjectsUserDutyService {
    /**
     * 查询项目中的职责
     * @param id
     * @return
     */
    ProjectsUserDuty getById(Integer id);
}
