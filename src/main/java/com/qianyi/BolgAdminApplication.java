package com.qianyi;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 千亦
 * @create 2022-11-06-14:28
 */
@SpringBootApplication
@MapperScan("com.qianyi.mapper")
public class BolgAdminApplication {
    public static void main(String[] args){
        SpringApplication.run(BolgAdminApplication.class,args);
    }
}
