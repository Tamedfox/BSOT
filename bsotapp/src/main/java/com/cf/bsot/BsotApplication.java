package com.cf.bsot;

import com.cf.bsot.common.logger.LoggerEnvironment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BsotApplication {

    public static void main(String[] args) {
        LoggerEnvironment.initLoggerEnvironment();
        SpringApplication.run(BsotApplication.class, args);
    }

}
