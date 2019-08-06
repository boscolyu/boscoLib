package org.bosco.jdk.java8.annotation;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.Documented;


public class AnnotationTest {


    @Test
    public void annotationTest() {

        CustomAnnotationImpl testImpl  = new CustomAnnotationImpl();

    }


    public void annotationTestWithRepeatable() {

    }
}
