package cn.edu.lingnan.projectmanagment.controller;

import ch.qos.logback.core.util.FileUtil;
import cn.edu.lingnan.projectmanagment.bean.Documents;
import cn.edu.lingnan.projectmanagment.service.ProjectService;
import cn.edu.lingnan.projectmanagment.service.impl.DocumentsServiceImpl;
import cn.edu.lingnan.projectmanagment.service.impl.ProjectsPackageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Author shaosen
 * @Description //TODO
 * @Date 16:29 2020/4/1
 */
@Controller
public class DocumentsController {
    @Autowired
    DocumentsServiceImpl documentsService;
    @Autowired
    ProjectService projectService;
    @Autowired
    ProjectsPackageServiceImpl projectsPakcageService;

//    @RequestMapping("/document")
//    public String text(@RequestParam("filename") String filename, @RequestParam("projectId") Integer projectId, @RequestParam("userId") Integer userid, Model model) {
//        System.out.println("文件名：" + filename);
//        System.out.println("项目id：" + projectId);
//        Documents documents1 = new Documents();
//        Documents documents2 = new Documents();
//        //获取文件名为filename和版本标识符为1的
//        documents1 = documentsService.getByVersionFlagAndName(1, filename);
//        System.out.println(documents1);
//        //获取文件名为filename和版本标识符为2的
//        documents2 = documentsService.getByVersionFlagAndName(2, filename);
//        System.out.println(documents2);
//        //判断有无VersionFlag为2的
//        if (documents2 == null) {
//            //只有版本号为1的，把d1赋值给d1
//            Documents documents = documents1;
//            System.out.println(documents);
//            //返回前端documents对象
//            model.addAttribute("documents1", documents);
//            //处理文件，返回前端地址
//            String addressDocument = FileUtil.DocumentsGetAddrress(documents);
//            //返回前端地址
//            model.addAttribute("addressdocument", addressDocument);
//        } else {
//            //返回前端documents对象
//            model.addAttribute("documents1", documents1);
//            model.addAttribute("documents2", documents2);
//            //处理文件，返回前端地址
//            String addressd1 = FileUtil.DocumentsGetAddrress(documents1);
//            System.out.println(addressd1);
//            String addressd2 = FileUtil.DocumentsGetAddrress(documents2);
//            System.out.println(addressd2);
//            //返回前端地址
//            model.addAttribute("addressd1", addressd1);
//            model.addAttribute("addressd2", addressd2);
//        }
//
//        //设置项目id
//        model.addAttribute("projectId", projectId);
//        //判断是否为项目组长
//        Integer power = 0;
//        if (projectService.getAdminByUserIdAndProjectId(userid, projectId) != null) {
//            power = 1;
//        } else {
//            power = null;
//        }
//        model.addAttribute("power", power);
//        return "text";
//    }
//
//    @RequestMapping("/getall")
//    public String getAll(@RequestParam("projectId") Integer projectId, Model model) {
//        List<Documents> List = documentsService.getAllByProjectId(projectId);
//        System.out.println(List);
//        model.addAttribute("allDocuments", List);
//        //设置项目id
//        model.addAttribute("projectId", projectId);
//        return "datatables";
//    }

//    @RequestMapping("/upload")
//    public String uploadFile(@RequestParam("file") MultipartFile[] files, @RequestParam("projectId") Integer projectId, Documents documents,
//                             @RequestParam("packageName1") String packageName, HttpServletRequest request, Model model) throws Exception {
//        System.out.println("项目id：" + projectId);
//        System.out.println("packageName:::" + packageName);
//        if (files.length == 0) {
//            return "failure";
//        }
//        String filePath = request.getServletContext().getRealPath("/");
//        for (MultipartFile file : files) {
//            //生成随机号
//            String uuid = UUID.randomUUID().toString();
//            uuid = uuid.replace("-", "");
//            //获取文件名
//            String oldFileName = file.getOriginalFilename();
//            int index = oldFileName.lastIndexOf("\\");
//            if (index != -1) {
//                oldFileName.substring(index + 1);
//            }
//            //拼接文件名，防止冲突
//            String newFileName = uuid + "-" + oldFileName;
//            System.out.println("D:/upload/" + newFileName);
//            //设置Documents的属性
//            documents.setName(oldFileName);
//            documents.setSerialNumber(uuid);
//            //以后需要修改projiectId
//            documents.setProjectId(projectId);
//            //装换文件类型，且读取代码行数
//            System.out.println("开始转换文件");
//            if (FileUtil.codeLine(FileUtil.multipartFileToFile(file)) != 0) {
//                documents.setCodeLineNumber(FileUtil.codeLine(FileUtil.multipartFileToFile(file)));
//            } else {
//                documents.setCodeLineNumber(0);
//            }
//            System.out.println("转换结束");
//            //控制上传确定版本号为2的
//            if (documentsService.getByVersionFlagAndName(2, oldFileName) != null) {
//                String msg = "存在未审核版本，不能上传，请通知项目组长审核";
//                System.out.println(msg);
//                model.addAttribute("msg", msg);
//                return "false";
//            }
//            //查询版本号
//            if (documentsService.getVersionByName(oldFileName) != null) {
//                documents.setVersion(documentsService.getVersionByName(oldFileName) + 1);
//                documents.setVersionFlag(2);
//            } else {
//                documents.setVersion(1);
//                documents.setVersionFlag(1);
//                //第一次插入统计代码行
//                //修改代码贡献量
//                Integer codeLine = documents.getCodeLineNumber();
//                ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//                UserProjectsController userProjectsController = ctx.getBean(UserProjectsController.class);
//                System.out.println("代码行修改：" + userProjectsController.update(codeLine, documents.getWriter(), documents.getProjectId()));
//                //插入record表
//                ReocrdController reocrdController = ctx.getBean(ReocrdController.class);
//                System.out.println("插入reocrd:::" + reocrdController.insert(documents, codeLine));
//            }
//            System.out.println(documents);
//            //插入数据库
//            documentsService.insert(documents);
//            //插入日志文件
//            ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//            DocumentsRecordController documentsRecordController = ctx.getBean(DocumentsRecordController.class);
//            //获取ip
//            String ip = IPUtil.getIP(request);
//            System.out.println("获取的ip:::" + ip);
//            System.out.println("插入日志数据库:::" + documentsRecordController.insert(documents, ip, 1));
//            //插入文件包表数据库
//            DocumentsPackageController documentsPackageController = ctx.getBean(DocumentsPackageController.class);
//            System.out.println("插入文件包表数据库：：：" + documentsPackageController.insertpackage(documents, packageName));
//
//            //写入磁盘
//            System.out.println("D:/upload/" + newFileName);
//            file.transferTo(new File("D:/upload/" + newFileName));
//        }
//        if (documents.getVersionFlag() == 2) {
//            ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//            Documents d1 = ctx.getBean(Documents.class);
//            Documents d2 = ctx.getBean(Documents.class);
//            //获取文件名为filename和版本标识符为1的
//            d1 = documentsService.getByVersionFlagAndName(1, documents.getName());
//            System.out.println(d1);
//            //获取文件名为filename和版本标识符为2的
//            d2 = documentsService.getByVersionFlagAndName(2, documents.getName());
//            System.out.println(d2);
//        }
//        //设置项目id
//        model.addAttribute("projectId", projectId);
//        model.addAttribute("filename", documents.getName());
//        model.addAttribute("userId", documents.getWriter());
//        return "redirect:text";
//    }
//
//    //修改文件状态
//    //-1表示以前没有用过的版本
//    //0表示以前通过版本
//    //1表示当前版本
//    //2表示要审核版本
//    @RequestMapping("/update")
//    public String updateFileState(@RequestParam("documentsName") String documentsName, Integer operate, HttpServletRequest request, Model model) {
//        //Integer operate表示操作的类型，1表示同意，把d1的versionFing改为0，d2的改为1
//        // 2表示不同意，把d2的改为0
//        System.out.println(documentsName);
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//        Documents d1 = ctx.getBean(Documents.class);
//        Documents d2 = ctx.getBean(Documents.class);
//        //获取文件名为filename和版本标识符为1的
//        d1 = documentsService.getByVersionFlagAndName(1, documentsName);
//        System.out.println(d1);
//        //获取文件名为filename和版本标识符为2的
//        d2 = documentsService.getByVersionFlagAndName(2, documentsName);
//        System.out.println(d2);
//        DocumentsRecordController documentsRecordController = ctx.getBean(DocumentsRecordController.class);
//        //获取ip
//        String ip = IPUtil.getIP(request);
//        System.out.println("获取的ip:::" + ip);
//        if (operate == 1) {
//            //同意修改文件，修改文件versionFlag值为1的变为0，值为2的变为1
//            d1.setVersionFlag(0);
//            System.out.println(d1);
//            System.out.println("修改d1文件确定号：" + documentsService.update(d1));
//            d2.setVersionFlag(1);
//            System.out.println(d2);
//            System.out.println("修改d2文件确定号：" + documentsService.update(d2));
//            //修改代码贡献量
//            Integer codeLine = d2.getCodeLineNumber() - d1.getCodeLineNumber();
//            UserProjectsController userProjectsController = ctx.getBean(UserProjectsController.class);
//            System.out.println("代码行修改：" + userProjectsController.update(codeLine, d2.getWriter(), d2.getProjectId()));
//            //插入record表
//            ReocrdController reocrdController = ctx.getBean(ReocrdController.class);
//            reocrdController.insert(d1, codeLine);
//            //插入日志表
//            documentsRecordController.insert(d2, ip, 2);
//        } else {
//            //不同意修改文件，只修改文件versionFlag值2的变为-1
//            d2.setVersionFlag(-1);
//            System.out.println("修改d2文件确定号：" + documentsService.update(d2));
//            documentsRecordController.insert(d2, ip, 2);
//        }
//        model.addAttribute("projectId", d2.getProjectId());
//        model.addAttribute("filename", d2.getName());
//        model.addAttribute("userId", operate);
//        return "redirect:text";
//    }
//
//    @RequestMapping("/updatedocuments")
//    public String updatedocuments(@RequestParam("projectId") Integer projectId, @RequestParam("filename") String filename,
//                                  @RequestParam("packageName") String packageName, @RequestParam("versionMessage") String versionMessage,
//                                  @RequestParam("creator") Integer creator,
//                                  HttpServletRequest request, Model model) {
//        System.out.println("projectId:::" + projectId + "------------------------------");
//        System.out.println("filename:::" + filename + "------------------------------");
//        System.out.println("packageName:::" + packageName + "------------------------------");
//        System.out.println("versionMessage:::" + versionMessage + "------------------------------");
//        System.out.println("creator:::" + creator + "------------------------------");
//        //查找documents
//        ApplicationContext ctx = new ClassPathXmlApplicationContext("application-context.xml");
//        Documents documents = ctx.getBean(Documents.class);
//        documents = documentsService.getByVersionFlagAndName(1, filename);
//        //修改版本信息
//        if (versionMessage != null || versionMessage.length() > 0) {
//            documents.setVersionMessage(versionMessage);
//        }
//        //修改包
//        if (packageName != null || packageName.length() > 0) {
//            DocumentsPackageController documentsPackageController = ctx.getBean(DocumentsPackageController.class);
//            System.out.println("修改包名：：" + documentsPackageController.updatedocumentsPackage(documents, packageName));
//        }
//        model.addAttribute("projectId", documents.getProjectId());
//        model.addAttribute("filename", documents.getName());
//        model.addAttribute("userId", creator);
//        return "redirect:text";
//    }

}
