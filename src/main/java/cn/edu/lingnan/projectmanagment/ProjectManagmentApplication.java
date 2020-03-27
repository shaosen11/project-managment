package cn.edu.lingnan.projectmanagment;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(value = "cn.edu.lingnan.projectmanagment.mapper")
public class ProjectManagmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectManagmentApplication.class, args);
    }

}
