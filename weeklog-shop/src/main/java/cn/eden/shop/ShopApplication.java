package cn.eden.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "cn.eden.shop")
@MapperScan("cn.eden.shop.dao")//扫描mapper接口
public class ShopApplication{
    public static void main(String[] args){
        SpringApplication.run(ShopApplication.class,args);
    }
}
