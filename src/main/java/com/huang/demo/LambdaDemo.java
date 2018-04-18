package com.huang.demo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author timkobe
 */
public class LambdaDemo {
    public static void main(String[] args) {
//        demo1();
//        demo2();
//        demo5();
//        demo6();
//        demo7();
        demo9();
    }

    /**
     * 用lambda表达式实现Runnable
     */
    public static void demo1(){
//        (params) -> expression 表达
//        (params) -> statement 申明
//        (params) -> { statements }
        //替换匿名类
        //old way
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("this is the old way >>>");
            }
        }).start();

        //new way
        new Thread(()-> System.out.println("this is the new way>>>>")).start();
    }

    /**
     * 使用Java 8 lambda表达式进行事件处理
     */
    public static  void demo2(){
        // Java 8之前：
        JButton show =  new JButton("Show");
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Event handling without lambda expression is boring");
            }
        });

// Java 8方式：
        show.addActionListener((e) -> {
            System.out.println("Light, Camera, Action !! Lambda expressions Rocks");
        });
    }

    /**
     * 使用lambda表达式对列表进行迭代
     */
    public static void demo3(){
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
         //old way
        for (String feature : features) {
            System.out.println(feature);
        }
        //new way
        features.forEach(p-> System.out.println(p));
        //
        features.forEach( System.out::println);
    }

    /**
     * 使用lambda表达式和函数式接口Predicate
     * 除了在语言层面支持函数式编程风格，Java 8也添加了一个包，叫做 java.util.function。
     * 它包含了很多类，用来支持Java的函数式编程。其中一个便是Predicate，
     * 使用 java.util.function.Predicate 函数式接口以及lambda表达式，可以向API方法添加逻辑，
     * 用更少的代码支持更多的动态行为。
     * 下面是Java 8 Predicate 的例子，展示了过滤集合数据的多种常用方法。
     * Predicate接口非常适用于做过滤。
     */
    public static void demo4(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
//        filter(languages,(p)->p.);
        System.out.println("Languages which starts with J :");
        filter(languages, (str)->str.startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str)->str.endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
//        filter(languages, (str)->str.length() > 4);
    }

    public static void filter(List<String> names, Predicate<String> condition) {
        for(String name: names)  {
            if(condition.test(name)) {
                System.out.println(name + " ");
            }
        }
        //另一种
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }

    /**
     *如何在lambda表达式中加入Predicate
     */
    public static void demo5(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
    // 甚至可以用and()、or()和xor()逻辑函数来合并Predicate，
    // 例如要找到所有以J开始，长度为四个字母的名字，你可以合并两个Predicate并传入
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream().filter(startsWithJ.and(fourLetterLong)).forEach(p-> System.out.println(p));
    }

    /**
     * Java 8中使用lambda表达式的Map和Reduce示例
     * 本例介绍最广为人知的函数式编程概念map。
     * 它允许你将对象进行转换。例如在本例中，我们将 costBeforeTax 列表的每个元素转换成为税后的值。
     * 我们将 x -> x*x lambda表达式传到 map() 方法，后者将其应用到流中的每一个元素。
     * 然后用 forEach() 将列表元素打印出来。使用流API的收集器类，可以得到所有含税的开销。
     * 有 toList() 这样的方法将 map 或任何其他操作的结果合并起来。
     * 由于收集器在流上做终端操作，因此之后便不能重用流了。
     * 你甚至可以用流API的 reduce() 方法将所有数字合成一个，下一个例子将会讲到
     */
    public static void demo6(){
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            System.out.println(price);
        }
        //一种
        Stream<Double> stream = costBeforeTax.stream().map(cost -> cost + 0.12 * cost);
        stream.forEach(p-> System.out.println(p));
        //二中直接追加
        costBeforeTax.stream().map(p->p+":sss").forEach(System.out::println);


        ///
        double total = 0;
        for (Integer cost : costBeforeTax) {
            double price = cost + .12*cost;
            total = total + price;
        }
        System.out.println("Total : " + total);
        Double aDouble = costBeforeTax.stream().map(p -> p + 0.123 * p).reduce((sum, cost) -> sum + cost).get();

    }

    /**
     *通过过滤创建一个String列表
     * 过滤是Java开发者在大规模集合上的一个常用操作，而现在使用lambda表达式和流API过滤大规模数据集合是惊人的简单。
     * 流提供了一个 filter() 方法，接受一个 Predicate 对象，即可以传入一个lambda表达式作为过滤逻辑。
     * 下面的例子是用lambda表达式过滤Java集合，将帮助理解。
     */
    public static void demo7(){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> list = languages.stream().filter(p -> p.length() > 3).collect(Collectors.toList());
        list.forEach(s -> System.out.println(s));
    }

    /**
     * 对列表的每个元素应用函数
     * 我们通常需要对列表的每个元素使用某个函数，例如逐一乘以某个数、除以某个数或者做其它操作。
     * 这些操作都很适合用 map() 方法，可以将转换逻辑以lambda表达式的形式放在 map() 方法里，
     * 就可以对集合的各个元素进行转换了，如下所示。
     */
    public static void demo8(){
        // 将字符串换成大写并用逗号链接起来
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    /**
     * 复制不同的值，创建一个子列表
     * 本例展示了如何利用流的 distinct() 方法来对集合进行去重。
     */
    public static void demo9(){
        // 用所有不同的数字创建一个正方形列表
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> collect = numbers.stream().distinct().sorted().collect(Collectors.toList());
        List<Integer> distinct = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.","Canada");
        List<String> strings = G7.stream().sorted().collect(Collectors.toList());
        System.out.printf("Original List : %s,  Square Without duplicates : %s %n", numbers, distinct);
    }

    /**
     * 计算集合元素的最大值、最小值、总和以及平均值
     * IntStream、LongStream 和 DoubleStream 等流的类中，有个非常有用的方法叫做 summaryStatistics() 。
     * 可以返回 IntSummaryStatistics、LongSummaryStatistics 或者 DoubleSummaryStatistic s，
     * 描述流中元素的各种摘要数据。在本例中，我们用这个方法来计算列表的最大值和最小值。
     * 它也有 getSum() 和 getAverage() 方法来获得列表的所有元素的总和及平均值。
     */
    public static void demo10(){

//获取数字的个数、最小值、最大值、总和以及平均值
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());
    }
//    Lambda表达式 vs 匿名类

// 既然lambda表达式即将正式取代Java代码中的匿名内部类，那么有必要对二者做一个比较分析。
// 一个关键的不同点就是关键字 this。匿名类的 this 关键字指向匿名类，而lambda表达式的 this 关键字指向包围lambda表达式的类。
// 另一个不同点是二者的编译方式。Java编译器将lambda表达式编译成类的私有方法。
// 使用了Java 7的 invokedynamic 字节码指令来动态绑定这个方法。
}
