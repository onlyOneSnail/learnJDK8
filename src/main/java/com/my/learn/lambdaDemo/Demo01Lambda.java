package com.my.learn.lambdaDemo;

import java.util.function.Consumer;

public class Demo01Lambda {

    public static void getMax(int[] arr) {
        int max = 0;
        for (int num : arr) {
            max += num;
        }
        System.out.println(max);
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 23, 42};

        getMax(arr);

        getMaxConsumer((int[] array) -> {
            int max = 0;
            for (int num : arr) {
                max += num;
            }
            System.out.println(max);
        }, arr);

        getMaxConsumer(Demo01Lambda::getMax,arr);

    }

    public static void getMaxConsumer(Consumer<int[]> consumer, int[] arr) {
        consumer.accept(arr);
        System.out.println("end_consumer");
    }
}
