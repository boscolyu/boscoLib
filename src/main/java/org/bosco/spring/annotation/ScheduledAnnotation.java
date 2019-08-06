package org.bosco.spring.annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableAutoConfiguration
@EnableScheduling
public class ScheduledAnnotation {

    public static void main(String []args) {
        SpringApplication.run(ScheduledAnnotationImpl.class, args);
    }
}
