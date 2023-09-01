package com.example.demo2;

import config.AzureBlobPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@ComponentScan(basePackages = {"service", "com.example.demo2"})
@ComponentScan(basePackageClasses = {
        AzureBlobPropertiesConfig.class
})

@SpringBootApplication
public class Demo2Application {


    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Demo2Application.class);
        Map<String, Object> map = new HashMap<>();
        application.setDefaultProperties(map);
        application.run(args);

    }

}
