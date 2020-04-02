package cn.edu.lingnan.projectmanagment.controller;

import cn.edu.lingnan.projectmanagment.utils.FtpUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 18:14 2020/4/1
 */
@Controller
public class UploadController {

    @GetMapping("/upload")
    public String upload() {
        return "upload";
    }

    @PostMapping("/upload")
    @ResponseBody
    public Object upload(@RequestParam("file") MultipartFile file) throws IOException {
        System.out.println("上传文件");
        //调用自定义的FTP工具类上传文件
        String fileName = FtpUtil.uploadFile(file);
        if (!StringUtils.isEmpty(fileName)){
            System.out.println("上传文件" + fileName);
        }
        System.out.println("上传失败");
        return fileName;
    }

}
