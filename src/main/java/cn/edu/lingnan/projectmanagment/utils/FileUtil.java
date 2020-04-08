package cn.edu.lingnan.projectmanagment.utils;

import cn.edu.lingnan.projectmanagment.bean.Documents;
import cn.edu.lingnan.projectmanagment.service.DocumentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;


public class FileUtil {
    @Autowired
    private DocumentsService documentsService;

    //获取行数
    public static Integer codeLine(File file) {
        try {
            if (file.exists()) {
                long fileLength = file.length();
                LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(file));
                lineNumberReader.skip(fileLength);
                int lines = lineNumberReader.getLineNumber();
                System.out.println("Total number of lines : " + lines);
                lineNumberReader.close();
                return lines;
            } else {
                System.out.println("File does not exists!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //将multipartFileToFile转为file
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        System.out.println("进入multipartFileToFile函数");
        File toFile = null;
        if (file.equals("") || file.getSize() <= 0) {
            file = null;
        } else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        System.out.println("退出函数");
        return toFile;
    }

    //获取流文件
    public static void inputStreamToFile(InputStream ins, File file) {
        System.out.println("进入inputStream函数");
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //获取文件路径
    public static String DocumentsGetAddrress(Documents documents) {
        System.out.println("文件" + documents);
        String address = "http://47.98.240.31/files/projects/" + documents.getProjectId() + "/"+ documents.getSerialNumber() + "-" + documents.getName();
        System.out.println("文件路径：：" + address);
        return address;
    }
}
