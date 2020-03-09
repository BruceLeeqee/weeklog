package cn.eden;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * https://blog.csdn.net/u011138533/article/details/52165577 测试学习
 * 1. 开发RestFulAPI
 * 2. 整合SpringSecurity
 */
@SpringBootApplication(scanBasePackages = "cn.eden")
@EnableJpaRepositories(basePackages = "cn.eden")
@MapperScan("cn.eden.repository.mybatisRepo.base")//扫描mapper接口
@EntityScan(basePackages = "cn.eden.repository.entity")
public class ProjectApplication {
    public static void main(String[] args){
        SpringApplication.run(ProjectApplication.class,args);
    }
}

