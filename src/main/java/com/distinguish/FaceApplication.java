package com.distinguish;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class FaceApplication {
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FaceApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(FaceApplication.class, args);
    }

}
