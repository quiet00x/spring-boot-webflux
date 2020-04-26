package com.example.webflux.demo.lambda;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/6 - 2:43
 * @author: Mr_Bangb
 * 遍历map的五种方式
 */
public class ForeachMapDemo {

    public static void main(String[] args) {

        Map<String, Object> map = initMap();

        // 1. 使用Iterator 遍历 HashMap的 EntrySet
        iteratorEntrySet(map);
        // 2. 使用Iterator 遍历 HashMap的 KeySet
        iteratorKeySet(map);
        // 3. jdk 1.7 使用 For Each 增强for循环遍历 EntrySet
        forEachEntrySet(map);
        // 4. 使用 jdk 1.8 的 lambda 表达式 遍历 map
        lambdaForEach(map);
        // 5. 使用 jdk 1.8 stream api 遍历 map
        streamApi(map);
    }

    /**
     *  1. 使用Iterator 遍历 HashMap的 EntrySet
     * @param map
     */
    private static void iteratorEntrySet(Map<String, Object> map) {
        Iterator<Map.Entry<String,Object>> iteratorEntrySet = map.entrySet().iterator();
        while (iteratorEntrySet.hasNext()) {
            Map.Entry<String,Object> entry = iteratorEntrySet.next();
            System.out.println("key: " + entry.getKey() + ",value:" + entry.getValue());
        }
        System.out.println("***************** 使用Iterator 遍历 HashMap的 EntrySet *****************");
    }

    /**
     *  2. 使用Iterator 遍历 HashMap的 KeySet
     * @param map
     */
    private static void iteratorKeySet(Map<String, Object> map) {
        Iterator<String> iteratorKeySet = map.keySet().iterator();
        while (iteratorKeySet.hasNext()) {
            String key = iteratorKeySet.next();
            System.out.println("key: " + key + ",value:" + map.get(key));
        }
        System.out.println("***************** 使用Iterator 遍历 HashMap的 EntrySet *****************");
    }

    /**
     *  3. jdk 1.7 使用 For Each 增强for循环遍历 EntrySet
     * @param map
     */
    private static void forEachEntrySet(Map<String, Object> map) {
        for(Map.Entry<String,Object> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ",value:" + entry.getValue());
        }
        System.out.println("***************** 使用 for each 循环遍历 EntrySet *****************");
    }

    /**
     * 4. 使用 jdk 1.8 的 lambda 表达式 遍历 map
     * @param map
     */
    private static void lambdaForEach(Map<String, Object> map) {
        map.forEach((key,value) -> {
            System.out.println("key: " + key + ",value:" + value);
        });
        System.out.println("***************** 使用 lambda 遍历 map *****************");
    }

    /**
     * 5. 使用 jdk 1.8 stream api 遍历 map
     * @param map
     */
    private static void streamApi(Map<String, Object> map){
        map.entrySet().stream().forEach((entry) -> {
            System.out.println("key: " + entry.getKey() + ",value:" + entry.getValue());
        });
        System.out.println("***************** 使用 stream api 遍历 map *****************");
    }

    private static Map<String,Object> initMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("name","hahhy");
        map.put("age","20");
        map.put("sex","girl");
        map.put("addr","hu bei wu han xin zhou");
        return map;
    }
}

