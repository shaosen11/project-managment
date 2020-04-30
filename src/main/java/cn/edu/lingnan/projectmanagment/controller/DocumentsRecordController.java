package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.Documents;
import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import cn.edu.lingnan.projectmanagment.bean.MyUserDetails;
import cn.edu.lingnan.projectmanagment.service.UserService;
import cn.edu.lingnan.projectmanagment.service.impl.DocumentsRecordServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.DocumentsServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 21:13 2020/4/3
 */
@Controller
public class DocumentsRecordController {
    @Autowired
    DocumentsRecordServiceImpl documentsRecordService;

    @Autowired
    DocumentsServiceImpl documentsService;

    @Autowired
    UserService userService;

    //1是上传了文件，等待审核
    //2是审核文件通过了
    //3是审核文件不通过
    //通过documents插入日志表
    public boolean insert(Documents documents, String ip, Integer operateType) {
        //为了让日志文件时间和插入数据库时间一样
        documents = documentsService.getByProjectsIdAndVersionAndName(documents.getProjectId(), documents.getVersion(), documents.getName());
        DocumentsRecord documentsRecord = new DocumentsRecord();
        documentsRecord.setProjectId(documents.getProjectId());
        documentsRecord.setUserId(documents.getUserId());
        documentsRecord.setOperateTime(documents.getUploadTime());
        documentsRecord.setIp(ip);
        //处理操作
        MyUserDetails user = userService.findById(documents.getUserId());
        System.out.println(user);
        //判断操作类型
        String operateMassage = null;
        if (operateType == 1) {
            operateMassage = user.getUsername() + "上传了" + documents.getName() + "，版本号为：" + documents.getVersion();
        } else if (operateType == 2) {
            operateMassage = user.getUsername() + "同意了第" + documents.getVersion() + "版本" + documents.getName() + "文件上传";
        } else if (operateType == 3) {
            operateMassage = user.getUsername() + "不同意第" + documents.getVersion() + "版本" + documents.getName() + "文件上传";
        }
        System.out.println(operateMassage);
        documentsRecord.setOperateMessage(operateMassage);
        System.out.println(documentsRecord);
        return documentsRecordService.insert(documentsRecord);
    }

    @RequestMapping("/getall")
    public String getAll(@RequestParam("projectId") Integer projectsId, Model model) {
        List<DocumentsRecord> List = documentsRecordService.getAllByProjectId(projectsId);
        System.out.println(List);
        model.addAttribute("allDocumentsRecord", List);
        model.addAttribute("projectsId", projectsId);
        return "datatables";
    }

    /*查找所有被删除的记录（管理员）*/
    @RequestMapping("/getAllDeleteDocumentsRecord")
    public String getAllDeleteDocumentsRecord(Model model) {
        List<DocumentsRecord> list = documentsRecordService.getAllDeleteDocumentsRecord();
        if (list.equals(" ")) {
            model.addAttribute("msg", "查询失败");
            return "failure";
        } else {
            model.addAttribute("msg", list);
            return "delete_documentsRecord";
        }
    }

    /*根据id撤消某一记录*/
    @RequestMapping("/undo")
    public String undo(@Param("id") int id, Model model) {
        System.out.println(id);
        boolean flag = documentsRecordService.undo(id);
        if (flag == true) {
            model.addAttribute("msg", "数据删除成功");
            return "forward:getAllDeleteDocumentsRecord";
        } else {
            model.addAttribute("msg", "数据删除失败");
            return "forward:getAllDeleteDocumentsRecord";
        }
    }
}

