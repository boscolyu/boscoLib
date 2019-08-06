package org.bosco.jdk.java8;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Supplier;

public class LambdaFuncIFTest {

    public LambdaFuncIFTest() {

    }

    @Test
    public void lambdaFunctionalInterfaceTest() {
        repeat(10, ()-> System.out.println("Hello World"));
    }

    public static void repeat(int n, Runnable action) {
        for (int i=0; i < 10; i++) action.run();
    }

    @Test
    public void lambdaFunctionalInterfaceTestWitnIntComsumer() {
        repeat(10, i -> System.out.println("Countdown" + (9 - i)));
    }

    public static void repeat(int n, IntConsumer action) {
        for (int i = 0; i < 10; i++) action.accept(i);
    }


    @Test
    public void lambdaFunctionalInterfaceTestWithCustom() {
        repeat(10, (i, j) -> System.out.println("Countdown to " + j + " : " + (j - i)));
    }

    public static void repeat(int n, BoscoRunnable action) {
        int j = 20;
        for (int i = 0; i < j; i++) action.run(i, j);
    }

    @Test
    public void lambdaFunctionalInterfaceTestWithSupplier() {
        repeatSupplier( 10, () -> "supplier test");
    }

    public static void repeatSupplier(int n, Supplier<String> supplier) {
        for (int i = 0; i < n; i++) System.out.println(supplier.get());
    }

    @Test
    public void lambdaFunctionalInterfaceTestWithConsumer() {
        repeatConsumer( 10, n -> System.out.println(n));
    }

    public static void repeatConsumer(int n, Consumer<String> consumer) {
        for (int i = 0; i < n; i++) consumer.accept("test");
    }
}
