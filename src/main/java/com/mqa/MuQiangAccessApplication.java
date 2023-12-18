package com.mqa;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class MuQiangAccessApplication {

    public static void main(String[] args) {
        SpringApplication.run(MuQiangAccessApplication.class, args);
        log.info("MuQiangAccessApplication start success...\n\n");
    }

}
