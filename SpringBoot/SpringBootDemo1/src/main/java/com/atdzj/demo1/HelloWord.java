package com.atdzj.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 *@SpringBootApplication来标注一个主程序，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWord {
    public static void main(String[] args) {
        //Spring应用启动起来
        SpringApplication.run(HelloWord.class,args);
    }
}
