package com.ling.project_backend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ling.project_backend.mapper")
public class ProjectBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectBackendApplication.class, args);
    }

}
