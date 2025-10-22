package org.idea.hackaton1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class Hackaton1Application {

    public static void main(String[] args) {
        SpringApplication.run(Hackaton1Application.class, args);
    }

}
