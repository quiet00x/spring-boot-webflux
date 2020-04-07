package com.example.webflux.demo;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/6 - 2:16
 * @author: Mr_Bangb
 */
public class ForeachDemo {

    public static void main(String[] args) {
        List<String> listStr = initListStr();

        AtomicInteger counts = new AtomicInteger();

        for (String string : listStr) {
            System.out.print(string);
            if (StringUtils.startsWith(string,"quiet")) {
                counts.getAndIncrement();
            }
        }
        System.out.println("********************* JDK 1.7 for each **********************");

        listStr.forEach(string -> {
                System.out.print(string);
                if (StringUtils.startsWith(string,"quiet")) {
                    counts.getAndIncrement();
                }
        });
        System.out.println("********************* JDK 1.8  stream **********************");

        long count = listStr.stream().filter(string -> StringUtils.startsWith(string,"quiet")).count();
        System.out.println(count);

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