package org.bosco.jdk;

import org.bosco.jdk.java8.ComparableName;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ComparableTest {



    @Test
    public void testComparable() {
        ComparableName name = new ComparableName("test1111111");
        ComparableName name1 = new ComparableName("test1111111");
        Assert.assertEquals(0, name.compareTo(name1));
    }

    @Test
    public void testComparableWithBigInteger() {

        BigInteger integer1 = new BigInteger("123123");
        BigInteger integer2 = new BigInteger("456456");
        BigInteger integer3 = new BigInteger("123123");
        Assert.assertEquals(-1, integer1.compareTo(integer2));
        Assert.assertEquals(1, integer2.compareTo(integer1));
        Assert.assertEquals(0, integer1.compareTo(integer3));

    }

    @Test
    public void testComparableWithInteger() {
        Integer integer1 = Integer.valueOf(123123);
        Integer integer2 = Integer.valueOf(456456);
        Assert.assertEquals(-1, integer1.compareTo(integer2));

    }

    @Test
    public void testComparableNameArraysSort() {


        ComparableName test = new ComparableName("test");
        ComparableName test1 = new ComparableName("test11");
        ComparableName test2 = new ComparableName("test222");
        ComparableName test3 = new ComparableName("test3333");
        ComparableName test4 = new ComparableName("test44444");

        ComparableName[] list = new ComparableName[5];

        list[0] = test4;
        list[1] = test3;
        list[2] = test2;
        list[3] = test1;
        list[4] = test;

        for (ComparableName ele : list) {
            System.out.println(ele.toString());
        }
        System.out.println("sorting");
        Arrays.sort(list);
        for (ComparableName ele : list) {
            System.out.println(ele.toString());
        }



    }

    @Test
    public void testComparableNameWithCollectionsSort() {
        ComparableName test = new ComparableName("test");
        ComparableName test1 = new ComparableName("test11");
        ComparableName test2 = new ComparableName("test222");
        ComparableName test3 = new ComparableName("test3333");
        ComparableName test4 = new ComparableName("test44444");

        ArrayList<ComparableName> list1 = new ArrayList<>();
        list1.add(test3);
        list1.add(test4);
        list1.add(test2);
        list1.add(test1);
        list1.add(test);
        for (ComparableName ele : list1) {
            System.out.println(ele.toString());
        }

        Collections.sort(list1);
        for (ComparableName ele : list1) {
            System.out.println(ele.toString());
        }
    }

    @Test
    public void testStringSort() {
        String fruit1 = "Apple";
        String fruit2 = "Banana";
        String fruit3 = "PineApple";
        String fruit4 = "Watermellon";
        String fruit5 = "Mellon";
        String fruit6 = "Strawberry";

        ArrayList<String> list1 = new ArrayList<>();
        list1.add(fruit1);
        list1.add(fruit2);
        list1.add(fruit3);
        list1.add(fruit4);
        list1.add(fruit5);
        list1.add(fruit6);

        for (String ele : list1) {
            System.out.println(ele);
        }

        Collections.sort(list1);
        for (String ele : list1) {
            System.out.println(ele);
        }
    }
}
