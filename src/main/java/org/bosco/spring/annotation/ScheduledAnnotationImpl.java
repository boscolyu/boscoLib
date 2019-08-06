package org.bosco.spring.annotation;

import ch.qos.logback.classic.Logger;
import org.bosco.jdk.java8.annotation.CustomAnnotationImpl;
import org.bosco.spring.annotation.properties.SpringProperties;
import org.bosco.spring.annotation.properties.SpringPropertiesWithYaml;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;


@RestController
@EnableAutoConfiguration
@Component
public class ScheduledAnnotationImpl {

    @Autowired
    private SpringProperties springProperties;

//    @Autowired
//    private SpringPropertiesWithYaml springPropertiesWithYaml;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ScheduledAnnotationImpl.class);

    public ScheduledAnnotationImpl() {
        logger.info("#######################################");
        logger.info("class : " + this.toString());
        logger.info("#######################################");
        System.out.println("class : " + this.toString());
    }

    @PostConstruct
    public void testCustemAnnotationImpl() {
        springProperties.toString();
        //springPropertiesWithYaml.toString();
        logger.info("#######################################");
        logger.info(this.getClass().getMethods().toString());
        logger.info("#######################################");
        System.out.println(this.getClass().getMethods().toString());
        run();
    }



    @Scheduled(fixedRate = 10 * 1000, initialDelay = 10 * 1000)
    public void run() {
        logger.info("#######################################");
        logger.info("class : " + this.toString());
        logger.info(this.getClass().getMethods().toString());
        logger.info("#######################################");
        System.out.println("class : " + this.toString());
        System.out.println(this.getClass().getMethods().toString());
    }
}
