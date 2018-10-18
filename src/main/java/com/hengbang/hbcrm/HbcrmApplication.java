package com.hengbang.hbcrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//缓存 spring 默认缓存方式
//@EnableCaching
//定时器
@EnableScheduling
public class HbcrmApplication {

    public static void main(String[] args) {
        SpringApplication.run(HbcrmApplication.class, args);
    }
}
