package org.bosco.jdk.java8;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;

public class LambdaTestWithMethodReference {

    Integer param ;


    public LambdaTestWithMethodReference(Integer param) {
        this.param = param;
    }
    @Test
    public void LambdaTestWithClassMethod() {
        ArrayList<Integer> list = new ArrayList();
        list.add(2);
        list.add(3);
        list.add(null);
        list.add(1);
        list.add(null);

        list.removeIf(Objects::isNull);
        list.forEach(x -> {System.out.println(x);});
        Collections.sort(list);
        list.forEach(System.out::println);

        Stream<LambdaTestWithMethodReference> stream = list.stream().map(LambdaTestWithMethodReference::new);
        stream.forEach(System.out::println);


    }
}
