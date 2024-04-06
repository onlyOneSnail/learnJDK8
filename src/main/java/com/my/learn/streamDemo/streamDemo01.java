package com.my.learn.streamDemo;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class streamDemo01 {
    @Test
    public void test() {
        //获取集合中姓张并且长度为三的并打印输出
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, "张三丰", "张翠三", "张三", "法外狂徒张", "周国平");
        ArrayList<String> nameStrings = new ArrayList<>();
        for (String name : strings) {
            if (name.startsWith("张")) {
                nameStrings.add(name);
            }
        }
        ArrayList<String> lengStrings = new ArrayList<>();
        for (String nameString : nameStrings) {
            if (nameString.length() == 3) {
                lengStrings.add(nameString);
            }
        }
        for (String lengString : lengStrings) {
            System.out.println(lengString);
        }

        strings.stream().filter(e -> e.startsWith("张")).filter(e -> e.length() == 3)
                .forEach(e -> System.out.println("lambda _ stream:" + e));

        strings.stream().filter(e -> e.startsWith("张")).filter(e -> e.length() == 3)
                .forEach(System.out::println);
    }

    @Test
    public void test02() {
        //获取stream的方式
        //1.collection接口默认实现了stream方法
        ArrayList<String> arrayList = new ArrayList<>();
        Stream<String> stream = arrayList.stream();

        HashMap<String, Integer> hashMap = new HashMap<>();
        Stream<String> stringStream = hashMap.keySet().stream();
        Stream<Map.Entry<String, Integer>> entryStream = hashMap.entrySet().stream();
        Stream<Integer> integerStream = hashMap.values().stream();

        //2.Stream.of方法可以将数组转为流
        String[] stringArray = {"my", "your", "your"};
        Stream<String> array = Stream.of(stringArray);

        Integer[] intArray = {5, 3, 2, 5, 2, 2};
        Stream<Integer> integerStream1 = Stream.of(intArray);

        //注意基本数据类型不行,因为基本数据类型转流之后对象是基本数据类型的数组如int[]而不是int
        int[] intArrays = {3, 4, 5, 6, 2};
        Stream<int[]> arrays = Stream.of(intArrays);
        List<Integer> collect = arrays.map(e -> e.length).collect(Collectors.toList());
        for (Integer integer : collect) {
            System.out.println(integer);
        }
    }
}
