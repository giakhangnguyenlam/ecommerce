package com.khangnlg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = "com.khangnlg")
@PropertySource(value = {
        "file:core/src/main/resources/application.properties",
        "file:report",
        "classpath:application.properties"
})
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}