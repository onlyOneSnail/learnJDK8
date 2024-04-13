package com.my.learn.optionalDemo;

import org.junit.Test;

import java.util.Optional;

public class OptionalDemoTest {

    @Test
    public void testOptionalOf(){
        //orElse在有值的时候会执行到返回值之前的语句,orElseGet在有值的时候不执行
        Optional<String> as = Optional.of("a");
        Optional<Object> o = Optional.ofNullable(null);
        String s = as.orElse(test());
//        String s = as.orElseGet(()->{
//            System.out.println("bbb");
//            return "kkk";
//        });
        System.out.println(s);
    }

    public static String test() {
        System.out.println("---");
        return "ass";
    }
}
