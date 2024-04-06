package com.my.learn.lambdaDemo;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Demo03LambdaFunction {
    //类名::方法引用
    @Test
    public void test() {
        Function<String, Integer> fun1 = (String str) -> {
            return str.length();
        };
        //类名::方法引用就是把()中的第一个入参作为调用方法的对象,()除了第一个入参之外的入参作为调用方法的入参
        Function<String, Integer> fun2 = String::length;

        BiFunction<String, Integer, String> fun3 = (String str, Integer length) -> {
            return str.substring(length);
        };

        BiFunction<String, Integer, String> fun4 = String::substring;

        String result = fun4.apply("helloWorld", 4);
        System.out.println(result);
    }

    @Test
    public void test02() {
        Supplier<Person> sup1 = () -> {
            return new Person();
        };
        Supplier<Person> sup2 = Person::new;
        Person person = sup2.get();
        person.toString();

        BiFunction<String, Integer, Person> bif = (String name, Integer age) -> {
            return new Person(name, age);
        };
        //在调用方法的时候会根据方法的入参个数和类型选择对应的构造方法
        BiFunction<String, Integer, Person> bif2 = Person::new;
        Person monkey = bif2.apply("猴哥", 500);
        monkey.toString();
    }

    @Test
    public void test3() {
        //数组的构造方法调用其实就是类名::new
        Function<Integer, String[]> fun = (Integer len) -> {
            return new String[len];
        };
        Function<Integer, String[]> fun2 = String[]::new;
        String[] strings = fun2.apply(5);
        System.out.println(strings.length);
    }

}
