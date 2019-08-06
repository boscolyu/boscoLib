package org.bosco.jdk.java8.annotation;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@RestController
@EnableAutoConfiguration
@CustomAnnotation()
public class CustomAnnotationImpl {

    private static final Logger logger = LoggerFactory.getLogger(CustomAnnotationImpl.class);

    @CustomAnnotation()
    String first = "first";

    @CustomAnnotation()
    String second = "second";

    @CustomAnnotation(includeName = false)
    String third = "third";


    public CustomAnnotationImpl() {
        logger.info("class : " + this.toString());
        System.out.println("class : " + this.toString());
    }

    @PostConstruct
    public void testCustemAnnotationImpl() {
        logger.info(this.getClass().getMethods().toString());
        System.out.println(this.getClass().getMethods().toString());
    }

    @RequestMapping("/")
    @ApiOperation(value = "test api note",
                    produces = "application/json",
                    notes = "description of test api")
    public String home() {
        return "Hello World of CustomAnnotationImpl!<br>" + CustomAnnotations.toString(this);
    }


}
