package org.bosco.jdk.java8;

import org.junit.Test;

import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

public class LambdaVariableTest {

    @Test
    public void lambdaTestWithVarable() {
        String first = "test";
        test(first);
    }

    private void test(String first) {
        //String test = "test";
        Consumer<String> consumer = (test) -> {
            System.out.println(test.length() - first.length());
        };
        report(consumer);
    }

    public static void report(Consumer<String> consumer) {
        String first = "test";
        consumer.accept(first);
    }
}
