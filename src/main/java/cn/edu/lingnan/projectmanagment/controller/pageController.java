package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.bean.DocumentsRecord;
import cn.edu.lingnan.projectmanagment.service.impl.DocumentsRecordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 0:19 2020/4/9
 */
@Controller
public class pageController {
    @Autowired
    DocumentsRecordServiceImpl documentsRecordService;

    @GetMapping("/documentRecordPage")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> pages(Integer page, Integer projectId, Integer userId) {
        System.out.println("当前页：" + page);
        System.out.println("当前项目：" + projectId);
        System.out.println("用户:" + userId);
        Map<String, Object> map = new HashMap<String, Object>();
        // 每页显示条数
        int pageSize = 5;

        try {
            // 获取总条目数
            Integer count = null;
            if(userId == 0){
                count = documentsRecordService.getDocumentsRecordCountByProjectId(projectId);
            }else {
                count = documentsRecordService.getDocumentsRecordCountByProjectIdAndUserId(projectId, userId);
            }
            // 计算总页数
            int totalPage = count / pageSize;
            // 不满一页的数据按一页计算
            if (count % pageSize != 0) {
                totalPage++;
            }
            // 当页数大于总页数，直接返回
            if (page > totalPage){
                return null;
            }
            // 计算sql需要的起始索引
            int offset = (page - 1) * pageSize;
            // 根据起始索引和页面大小去查询数据
            List<DocumentsRecord> list = null;
            if(userId == 0){
                list = documentsRecordService.getDocumentsRecordPageByProjectId(projectId, offset, pageSize);
            } else {
                list = documentsRecordService.getDocumentsRecordPageByProjectIdAndUserId(projectId, userId, offset, pageSize);
            }
            // 封装数据，并返回
            map.put("page", page);
            map.put("pageSize", pageSize);
            map.put("totalPage", totalPage);
            map.put("list", list);

            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("获取分页数据失败" + e);
            return new ResponseEntity<Map<String, Object>>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
