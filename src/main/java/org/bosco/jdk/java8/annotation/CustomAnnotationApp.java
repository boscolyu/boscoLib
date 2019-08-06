package org.bosco.jdk.java8.annotation;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
public class CustomAnnotationApp {

    @Resource(name="impl")
    CustomAnnotationImpl annotationImple;

    public static void main(String[] args) {
        System.out.println(getStringofDeprecated());
        SpringApplication.run(CustomAnnotationImpl.class, args);
    }

    @Deprecated(since = "9", forRemoval = true)
    public static String getStringofDeprecated() {
        int version = new Integer("10").intValue();
        return "deprecated methods of " +  version;
    }
}
