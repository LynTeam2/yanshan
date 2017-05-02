package com.xingkong.lyn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lyn on 2017/4/28.
 */
@SpringBootApplication(scanBasePackages = { "com.xingkong.lyn" })

public class StartApp {
    public static void main(String[] args){
        SpringApplication.run(StartApp.class,args);
    }
}
