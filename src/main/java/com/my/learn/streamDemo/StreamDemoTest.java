package com.my.learn.streamDemo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.LongStream;

public class StreamDemoTest {
    private static long times = 50000000000L;
    private long start;

    @Before
    public void init(){
        start = System.currentTimeMillis();
    }

    @After
    public void destroy() {
        long end = System.currentTimeMillis();
        System.out.println("消耗时间: " + (end - start));
    }

    @Test
    public void testForParallel(){
        LongStream.rangeClosed(0,times).parallel().reduce(0,Long::sum);
    }
    @Test
    public void testForNormal(){
        LongStream.rangeClosed(0,times).reduce(0,Long::sum);
    }
    @Test
    public void testForFor(){
        int result = 0;
        for (int j = 0; j < times; j++) {
            result += j;
        }
    }
}
