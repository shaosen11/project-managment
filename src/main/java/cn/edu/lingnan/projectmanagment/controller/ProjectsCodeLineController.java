package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.ProjectsCodeLine;
import cn.edu.lingnan.projectmanagment.service.ProjectsService;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectsCodeLineServiceImpl;
import cn.edu.lingnan.projectmanagment.utils.DateFromatUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 20:35 2020/4/3
 */
@Controller
public class ProjectsCodeLineController {
    @Autowired
    private ProjectsCodeLineServiceImpl projectsCodeLineService;
    @Autowired
    private ProjectsService projectsService;

    public boolean insert(Integer projectsId) {
        System.out.println("projectsId" + projectsId + "----------------------------------------");
        ProjectsCodeLine projectsCodeLine = projectsCodeLineService.getProjectsCodeLineByProjectIdAndToday(projectsId);
        System.out.println("projectsCodeLine:::" + projectsCodeLine + "-------------------------------------------");
        //判断今天有没有记录
        if (projectsCodeLine != null) {
            //有记录，修改记录
            projectsCodeLine.setCodeLineNumber(projectsService.getById(projectsId).getCodeLineNumber());
            return projectsCodeLineService.update(projectsCodeLine);
        } else {
            //新建对象
            ProjectsCodeLine projectsCodeLine1 = new ProjectsCodeLine();
            projectsCodeLine1.setProjectsId(projectsId);
            projectsCodeLine1.setCodeLineNumber(projectsService.getById(projectsId).getCodeLineNumber());
            return projectsCodeLineService.insert(projectsCodeLine1);
        }
    }

    /**
     * 项目总代码行折线图
     * @param projectId
     * @return
     */
    @GetMapping("/getLineChartDate")
    @ResponseBody
    public List getLineChartDate(Integer projectId) {
        List<ProjectsCodeLine> projectsCodeLines = projectsCodeLineService.getAllProjectsCodeLineByProjectId(projectId);
        return projectsCodeLines;
    }
}

