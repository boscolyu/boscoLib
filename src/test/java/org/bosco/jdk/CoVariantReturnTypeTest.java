package org.bosco.jdk;

import org.junit.Test;

/**
 * Created by bosco lyu on 2017. 4. 20..
 */
public class CoVariantReturnTypeTest {

    @Test
    public void TestCovariantReturnType() {
        CovariantSuperClass superClass = new CovariantSuperClass();
        superClass.message();

        CovariantSubClass sub = new CovariantSubClass();
        sub.message();

        superClass = new CovariantSubClass();
        superClass.message();
    }
}
