package com.my.learn.streamDemo;

import com.my.learn.lambdaDemo.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCollection {
    @Test
    public void testStreamToCollection() {
        List<Integer> collect = Stream.of(22, 53, 24, 75, 22, 3).collect(Collectors.toList());
        ArrayList<Integer> collect1 = Stream.of(22, 53, 24, 75, 22, 3).collect(Collectors.toCollection(ArrayList::new));

        Set<Integer> collect2 = Stream.of(22, 53, 24, 75, 22, 3).collect(Collectors.toSet());
        //Collectors.toCollection自定义返回集合类型
        HashSet<Integer> collect3 = Stream.of(22, 53, 24, 75, 22, 3).collect(Collectors.toCollection(HashSet::new));
    }

    @Test
    public void testStreamToArray() {
        //无参toarray返回object
        Object[] objects = Stream.of(22, 53, 24, 75, 22, 3).toArray();
        //有参toarray返回构造的数组类型,不能是基础数据类型
        Integer[] integers = Stream.of(22, 53, 24, 75, 22, 3).toArray(Integer[]::new);
        //如果想基础数据类型数组可以先转对应基础数据类型的流再转数组
        int[] ints = Stream.of(22, 53, 24, 75, 22, 3).mapToInt((a) -> {
            return a.intValue();
        }).toArray();

        Integer[] integers2 = Stream.of(22, 53, 24, 75, 22, 3).toArray(Integer[]::new);
        //等价于Integer[]::new
        for (Integer integer : integers2) {
            System.out.println(integer);
        }
    }

    @Test
    public void testStreamToSum() {
        //maxBy和max效果是一样的,区别暂时没找到
        Optional<Integer> collect = Stream.of(22, 53, 24, 75, 22, 3).collect(Collectors.maxBy((a, b) -> {
            return a - b;
        }));
        System.out.println(collect.get());

        Optional<Integer> collect1 = Stream.of(22, 53, 24, 75, 22, 3).collect(Collectors.minBy((a, b) -> {
            return a - b;
        }));
        System.out.println(collect1.get());

        Integer collect2 = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10))
                .collect(Collectors.summingInt(e -> e.getAge()));
        System.out.println(collect2);

        Double collect3 = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10))
                .collect(Collectors.averagingLong(e -> e.getAge()));
        System.out.println(collect3);

        Long collect4 = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10)).collect(Collectors.counting());
        System.out.println(collect4);

    }

    @Test
    public void testStreamToMaxCompareMaxBy() {
        Optional<Person> max = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10))
                .max(Person::compareTo);
        System.out.println("max" + max.get());

        Optional<Person> collect = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10))
                .collect(Collectors.maxBy(Person::compareTo));
        System.out.println("maxby" + collect.get());
    }

    @Test
    public void testStreamToGroup() {
        Map<Integer, List<Person>> collect = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10))
                .collect(Collectors.groupingBy(Person::getAge));
        for (Map.Entry<Integer, List<Person>> entry : collect.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }

        Map<String, List<Person>> collect1 = Stream.of(new Person("as", 5), new Person("kk", 7),
                new Person("kvsk", 1), new Person("kfdsk", 10))
                .collect(Collectors.groupingBy((a) -> {
                    if (a.getName().length() > 4) {
                        return "长名字";
                    } else if (a.getName().length() > 3) {
                        return "简称";
                    } else {
                        return "缩写";
                    }
                }));
        for (Map.Entry<String, List<Person>> entry : collect1.entrySet()) {
            System.out.println(entry.getKey() + "--" + entry.getValue());
        }
        //遍历的BiConsumer入参必须是当前参数类型或者其父类<? super K, ? super V>
        collect1.forEach((Object key, List<Person> value) -> {
            System.out.println(key + value.toString());
        });
    }

    @Test
    public void testStreamToMultiGroupBy() {
        //多级分组
        Map<String, Map<String, List<Person>>> collect1 = Stream.of(new Person("as", 5, "这里"), new Person("kk", 7, "这里"),
                new Person("kvsk", 1, "那里"), new Person("kfdsk", 10, "那里"))
                .collect(Collectors.groupingBy((a) -> {
                    if (a.getName().length() > 4) {
                        return "长名字";
                    } else if (a.getName().length() > 3) {
                        return "简称";
                    } else {
                        return "缩写";
                    }
                }, Collectors.groupingBy(e -> {
                    if (e.getAddress().contains("这")) {
                        return "这里";
                    } else {
                        return "other";
                    }
                })));
        collect1.forEach((k,v)->{
            System.out.println(k + "--" + v);
        });
    }

    @Test
    public void testStreamToPartitionBy() {
        //分区,其实是特殊的groupBy,返回值是true和false
        Map<Boolean, List<Person>> collect = Stream.of(new Person("as", 5, "这里"), new Person("kk", 7, "这里"),
                new Person("kvsk", 1, "那里"), new Person("kfdsk", 10, "那里"))
                .collect(Collectors.partitioningBy((e) -> {
                    return e.getAge() > 5;
                }));
        collect.forEach((k,v) -> {
            System.out.println(k + " == " + v);
        });
    }

    @Test
    public void testStreamToPartitionByCompareGroupBy() {
        //分组
        Map<Boolean, List<Person>> collect = Stream.of(new Person("as", 5, "这里"), new Person("kk", 7, "这里"),
                new Person("kvsk", 1, "那里"), new Person("kfdsk", 10, "那里"))
                .collect(Collectors.groupingBy(e ->{if (e.getAge() > 5){
                    return true;
                }else {
                    return false;
                }}));
        collect.forEach((k,v) -> {
            System.out.println(k + " == " + v);
        });
    }

    @Test
    public void testStreamToJoining() {
        //joining
        String collect = Stream.of(new Person("as", 5, "这里"), new Person("kk", 7, "这里"),
                new Person("kvsk", 1, "那里"), new Person("kfdsk", 10, "那里"))
                .map(Person::getAddress)
                .collect(Collectors.joining(",","<这是前缀>","<这是后缀>"));
        System.out.println(collect);
    }


}
