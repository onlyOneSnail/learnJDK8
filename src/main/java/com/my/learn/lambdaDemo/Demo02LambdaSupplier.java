package com.my.learn.lambdaDemo;

import org.junit.Test;

import java.util.Date;
import java.util.function.Supplier;

public class Demo02LambdaSupplier {
    //对象方法引用
    @Test
    public void test() {
        Date now = new Date();
        Supplier<Long> sup1 = () -> {
            return now.getTime();
        };

        Supplier<Long> sup2 = now::getTime;

        //lambda调用注意事项
        //1.调用的方法的参数必须和lambda中实现的抽象方法参数相同
//        Supplier<Long> sup3 = now::setTime;
        //2.调用的方法的返回值类型必须和lambda中实现的抽象方法返回值相同
//        Supplier<Long> sup4 = now::toGMTString;
    }

    //静态方法引用
    @Test
    public void testInterval() {
        Supplier<Long> sup1 = System::currentTimeMillis;
        Long aLong = sup1.get();
        System.out.println("static:" + aLong);
    }
}
