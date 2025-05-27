package com.rq.rqwfkj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.rq.rqwfkj.mapper")
public class RqWfkjApplication {

    public static void main(String[] args) {

        SpringApplication.run(RqWfkjApplication.class, args);
    }

}
