package org.bosco.jdk;

/**
 * Created by boscolyu on 2017. 4. 20..
 */
public class CovariantSuperClass {

    CovariantSuperClass get() {
        return this;
    }


    void message() {
        System.out.println("welcom to CovariantSuperClass");
    }
}
