package org.bosco.jdk.java8;

import org.junit.Test;

/**
 * Created by boscolyu on 2017. 2. 17..
 */
public class LambdaTestWithRunnable {

    @Test
    public void testLambdaExpressionWithRunnable() {

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello world one~!");
            }
        };

        int i = 10;
        int j = 100;
        int mount = 1000;

        /*Runnable r2 = () -> System.out.println("Hellow world two~!");

        r1.run();
        r2.run();*/

    }
}
