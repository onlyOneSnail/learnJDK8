package com.my.learn.streamDemo;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamThread {
    @Test
    public void testThread(){
        ArrayList<Integer> collect = Stream.of(1, 4, 2, 5, 2).filter(e -> {
            System.out.println("threadName --" + Thread.currentThread().getName());
            return true;
        }).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
    }

    @Test
    public void testGetParallelThread(){
        ArrayList<Object> list = new ArrayList<>();
        Collections.addAll(list,1,5,3,5,3);
        //直接获取并行流
        Stream<Object> objectStream = list.parallelStream();
        ArrayList<Object> collect = objectStream.filter(e -> {
            System.out.println("threadName --" + Thread.currentThread().getName());
            return true;
        }).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(collect);
        //串行流转并行流
        Stream<Object> parallel1 = list.stream().parallel();
        //串行流转并行流
        Stream<Integer> parallel = Stream.of(1, 4, 2, 5, 2).parallel();
    }
}
