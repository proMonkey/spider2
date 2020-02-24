package com.lmy.spider2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication()
public class Spider2Application extends SpringBootServletInitializer {

    private static final Logger logger = LoggerFactory.getLogger(Spider2Application.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Spider2Application.class);
    }

    public static void main(String[] args) throws Exception {
        logger.info("程序启动开始！！！！！！！");
        SpringApplication.run(Spider2Application.class, args);
    }



}
