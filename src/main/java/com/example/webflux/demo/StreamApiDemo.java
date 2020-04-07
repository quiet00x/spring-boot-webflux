package com.example.webflux.demo;

import com.example.webflux.domain.UserBean;

import java.util.*;
import java.util.stream.Stream;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/6 - 23:16
 * @author: Mr_Bangb
 */
public class StreamApiDemo {

    public static void main(String[] args) {
        // 获取 Stream
        // 1. 数组获取 Stream
        String[] strArr = new String[] {"ab", "cd", "ef"};
        Stream<String> streamStr = Arrays.stream(strArr);

        // 2. Object 获取 Stream
        UserBean userBean = initUserBean();
        Stream<UserBean> streamObj = Stream.of(userBean);

        // 3. list 获取Stream
        List<UserBean> userList = initListStr();
        Stream<UserBean> streamList = userList.stream();

        // 4. map 获取Stream
        Map<String, Object> map = initMap();
        Stream<Map.Entry<String, Object>> streamMap = map.entrySet().stream();

        // 遍历 forEach
        streamStr.forEach(string -> System.out.println(string));
        System.out.println("*********************Array Stream foreach*********************");
        streamObj.forEach(obj -> System.out.println(obj));
        System.out.println("*********************Object Stream foreach*********************");
        streamList.forEach(user1 -> System.out.println(user1));
        System.out.println("*********************List Stream foreach*********************");
        streamMap.forEach(entry -> System.out.println("key:" + entry.getKey() + ",value:" + entry.getValue()));
        System.out.println("*********************Map Stream foreach*********************");

        // 排序 sorted
        userList.stream().sorted(Comparator.comparing(UserBean::getAge)).forEach(user2 -> System.out.println(user2));
        System.out.println("*********************Stream sorted *********************");

        // 过滤 filter
        userList.stream().filter((UserBean user3) -> user3.getId() > 2).forEach(user3 -> System.out.println(user3));
        System.out.println("*********************Stream filter *********************");

        // limit()
        userList.stream().limit(3).forEach(user4 -> System.out.println(user4));
        System.out.println("*********************Stream limit *********************");

        // skip()
        userList.stream().skip(3).forEach(user5 -> System.out.println(user5));
        System.out.println("*********************Stream skip *********************");

        // distinct
        userList.stream().distinct().forEach(user6 -> System.out.println(user6));
        System.out.println("*********************Stream distinct *********************");
    }

    private static List<UserBean> initListStr() {
        List<UserBean> listStr = new ArrayList<>();
        UserBean user1 = new UserBean();
        UserBean user2 = new UserBean();
        UserBean user3 = new UserBean();
        UserBean user4 = new UserBean();

        user1.setId(1L);
        user2.setId(2L);
        user3.setId(3L);
        user4.setId(1L);

        user1.setAge("20");
        user2.setAge("22");
        user3.setAge("24");
        user4.setAge("20");

        user1.setSex('1');
        user2.setSex('0');
        user3.setSex('0');
        user4.setSex('1');

        user1.setUserName("hahhy");
        user2.setUserName("slag");
        user3.setUserName("quiet");
        user4.setUserName("hahhy");

        listStr.add(user1);
        listStr.add(user2);
        listStr.add(user3);
        listStr.add(user4);
        return listStr;
    }

    private static UserBean initUserBean() {
        UserBean user = new UserBean();

        user.setId(5L);
        user.setAge("28");
        user.setSex('1');
        user.setUserName("prayer");

        return user;
    }

    private static Map<String, Object> initMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("userId","6");
        map.put("userName","yoyo");
        map.put("userSex","0");
        map.put("userAge","28");
        return map;
    }

}