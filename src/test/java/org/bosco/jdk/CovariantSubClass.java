package org.bosco.jdk;

/**
 * Created by boscolyu on 2017. 4. 20..
 */
public class CovariantSubClass extends CovariantSuperClass {


    @Override
    CovariantSubClass get() {
        return this;
    }

    @Override
    void message() {
        System.out.println("welcom to CovariantSubClass");
    }
}
