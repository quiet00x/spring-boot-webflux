package com.example.webflux.demo.optional;

import org.apache.commons.lang3.StringUtils;

import javax.sound.midi.SoundbankResource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/4/17 - 23:54
 * @author: Mr_Bangb
 * JDK1.8 Optional容器的应用
 * 主要应用应该结合 lambda表达式
 */
public class OptionalDemo {

    public static void main(String[] args) {
        optionalStrDemo();
        optionalObjDemo();
    }

    /**
     * Optional 包装String
     * @return
     */
    private static void optionalStrDemo() {
        //------------< 获取Optional对象 >------------
        String strNotNull = "hello";
        String strNull = null;

        // 该方法 要求所装载的对象非null，若装载对象为null 会出现空指针异常
        Optional<String> optNotNull = Optional.of(strNotNull);

        // 该方法 要求允许装载的对象为null
        Optional<String> optNull = Optional.ofNullable(strNull);

        // 获取 Empty
        Optional<String> optEmpty = Optional.empty();

        // ------------< get() 获取容器所装载的对象 >------------
        //  输出 hello
        System.out.println(optNotNull.get());

        // value == null throw new NoSuchElementException
//        System.out.println(optNull.get());

        // 输出 Optional.empt
        System.out.println(optEmpty);

        // ------------< isPresent return value != null >------------
        // 输出 true
        System.out.println(optNull.isPresent());

        // 输出 false
        System.out.println(optNotNull.isPresent());

        // 输出 false
        System.out.println(optEmpty.isPresent());

        //------------< ifPresent 有内容，则执行 Optional 定义的抽象方法 void  consumer.accept(value)抽象方法  >------------
        // 而 Consumer 是一个函数式接口
        // 无内容输出
        optNull.ifPresent(s -> System.out.println(s));
        // 输出hello
        optNotNull.ifPresent(s -> System.out.println(s));
        // 无内容输出
        optEmpty.ifPresent(s -> System.out.println(s));

        //------------< filter 有内容 则执行 boolean predicate.test(value) 方法 对 Optional过滤
        // Predicate 是一个函数式接口 如果test 返回为true 则返回Optional 否则返回empty

        // 如果 optional不为空，并且以 字符串a 结尾 则返回 optional对象
        Optional<String> filterValue = optNotNull.filter(s -> StringUtils.endsWith(s, "a"));
        // 此处返回 false
        System.out.println(filterValue.isPresent());

        //------------< map 有内容 则执行 T mapper.apply(value) 方法 返回 Optional 对象
        // 如果 Optional 对象不为空 则返回 apply的结果
        // 输出 方法的返回值
        System.out.println(optNotNull.map(s -> "方法的返回值").get());

        //------------< orElse 没有内容，则返回
        // 如果 Optional 对象不为空  则返回 value 若未空 则返回orther
        // 输出 如果optional为空，则返回orther
        System.out.println(optNull.orElse("如果optional为空，则返回orther"));

        // ------------< map 返回的是Optional 对象，orElse 返回的是 value
        // 所处可以配合使用 最终返回 所需要的notNull 的 value
        // java notNull模式

        String valueRet = optNotNull.map(s -> "方法的返回值").orElse("empty 则返回 other");
        // 最终输出 方法的返回值
        System.out.println(valueRet);

        String valueResult2 = optNull.map(s -> "方法的返回值").orElse("empty 则返回 other");
        // 最终输出 empty 则返回 other
        System.out.println(valueResult2);
    }

    /**
     * Optional 包装 Object
     * @return
     */
    private static void optionalObjDemo() {
        List<String> listStr = new ArrayList<>();
        Optional<List<String>> optional = Optional.ofNullable(listStr);
        System.out.println(optional.get());
    }

}