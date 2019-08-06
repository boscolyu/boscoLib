package org.bosco.jdk.java8;

import org.junit.Test;

/**
 * Created by boscolyu on 2017. 2. 19..
 */
public class LambdaInterfaceTest {

    @Test
    public void testLambdaInterface() {

        LambdaInterface inter = (int mount) -> {
            mount++;
            System.out.println(mount);
        };


        inter.run(1999);



    }

    @Test
    public void testThreadWithLambda() {
        new Thread(new Runnable() {
            public void run() {
                System.out.println("전통적인 방식의 일회용 스레드 생성");
            }
        }).start();

        new Thread(()-> System.out.println("람다 표현식을 사용한 일회용 스레드 생성")).start();
    }
}
