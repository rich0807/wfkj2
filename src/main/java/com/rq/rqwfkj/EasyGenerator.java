package com.rq.rqwfkj;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.nio.file.Paths;

public class EasyGenerator {
 public static void main(String[] args) {
     FastAutoGenerator.create("jdbc:mysql://localhost:3306/wfkj2?characterEncoding=utf-8&useSSL=false&serverTimezone=UTC", "root", "123456")
             .globalConfig(builder -> builder
                     .author("RQ")
                     .outputDir(Paths.get(System.getProperty("user.dir")) + "/src/main/java")
                     .commentDate("yyyy-MM-dd")
             )
             .packageConfig(builder -> builder
                     .parent("com.rq.rqwfkj.minsu")
                     .entity("entity")
                     .mapper("mapper")
                     .service("service")
                     .serviceImpl("service.impl")
                     .xml("mapper.xml")
             )
             .strategyConfig(builder -> builder
                     .entityBuilder()
                     .enableLombok()
             )
             .templateEngine(new FreemarkerTemplateEngine())
             .execute();
 }
}
