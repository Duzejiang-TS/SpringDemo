package com.dzj.devinsvminiapi;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@MapperScan(basePackages = "com.dzj.mapper")
@ComponentScan(basePackages = {"com.dzj","org.n3r.idworker"})
public class DevinsvMiniApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevinsvMiniApiApplication.class, args);
    }
}
