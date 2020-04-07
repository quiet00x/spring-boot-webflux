package com.example.webflux.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/6 - 2:16
 * @author: Mr_Bangb
 * 遍历list的4种方式
 */
public class ForeachListDemo {

    private final static AtomicInteger counts = new AtomicInteger();

    public static void main(String[] args) {

        List<String> listStr = initListStr();
        // 1. jdk 1.7 Iterator 遍历list
        iteratorList(listStr);
        // 2. jdk 1.7 增强for循环遍历列表
        foreachList(listStr);
        // 3. jdk1.8 lambda 遍历 列表
        lambdaList(listStr);
        // 4. jdk1.8 stream api 遍历 列表
        streamList(listStr);

    }

    /**
     * 1. jdk 1.7 Iterator 遍历list
     * @param listStr
     */
    private static void iteratorList(List<String> listStr) {
        Iterator<String> iterator = listStr.iterator();

        while (iterator.hasNext()) {
            String string = iterator.next();
            System.out.println(string);
            if (StringUtils.startsWith(string,"quiet")) {
                counts.getAndIncrement();
            }
        }
        System.out.println("sounts:" + counts);
        System.out.println("********************* JDK 1.7 iterator **********************");

    }

    /**
     * 2. jdk 1.7 增强for循环遍历列表
     * @param listStr
     */
    private static void foreachList(List<String> listStr) {
        for (String string : listStr) {
            System.out.println(string);
            if (StringUtils.startsWith(string,"quiet")) {
                counts.getAndIncrement();
            }
        }
        System.out.println("sounts:" + counts);
        System.out.println("********************* JDK 1.7 for each **********************");

    }

    /**
     * 3. jdk1.8 lambda 遍历 列表
     * @param listStr
     */
    private static void lambdaList(List<String> listStr) {
        listStr.forEach(string -> {
            System.out.println(string);
            if (StringUtils.startsWith(string,"quiet")) {
                counts.getAndIncrement();
            }
        });
        System.out.println("sounts:" + counts);
        System.out.println("********************* JDK 1.8  lambda **********************");
    }

    /**
     * 4. jdk1.8 stream api 遍历 列表
     * @param listStr
     */
    private static void streamList(List<String> listStr) {
        // 该种方式相对最安全
        Stream<String> stream = listStr.stream();

        stream.forEach(string -> System.out.println(string));

        long count = stream.filter(string -> StringUtils.startsWith(string,"quiet")).count();

        System.out.println("sounts:" + counts);
        System.out.println("********************* JDK 1.8  stream **********************");
    }

    private static List<String> initListStr() {
        List<String> listStr = new ArrayList<>();
        listStr.add("quiet");
        listStr.add("hahhy");
        listStr.add("slag");
        listStr.add("prayer");
        listStr.add("quiet00x");
        listStr.add("quiet00j");
        return listStr;
    }

}