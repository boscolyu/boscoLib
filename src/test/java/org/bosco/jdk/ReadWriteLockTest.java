package org.bosco.jdk;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by boscolyu on 2017. 4. 7..
 */


public class ReadWriteLockTest {

    @Test
    public void readWriteLockTest() {

        Thread thread = new TestThread();
        thread.start();

    }
}
