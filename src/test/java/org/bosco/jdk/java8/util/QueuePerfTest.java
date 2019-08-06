package org.bosco.jdk.java8.util;

import org.junit.Test;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by boscolyu on 2017. 7. 3..
 */
public class QueuePerfTest {

    @Test
    public void testQueuePerformance() {

        LinkedBlockingQueue linkedBQueue = new LinkedBlockingQueue();
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();
        queue.add(new Object());
        /*PriorityBlockingQueue priorityQueue = new PriorityBlockingQueue();
        priorityQueue.add(new Object());
        priorityQueue.add(new Object());*/
    }
}
