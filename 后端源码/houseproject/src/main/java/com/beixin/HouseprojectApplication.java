package com.beixin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.beixin.dao")
public class HouseprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(HouseprojectApplication.class, args);
    }

}
