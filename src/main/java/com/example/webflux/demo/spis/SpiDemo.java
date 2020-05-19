package com.example.webflux.demo.spis;

import com.example.webflux.demo.spis.impl.Dog;
import com.example.webflux.demo.spis.interfaces.IShut;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/13 - 0:42
 * @author: Mr_Bangb
 */
public class SpiDemo {
    public static void main(String[] args) {
        ServiceLoader<IShut> iShuts = ServiceLoader.load(IShut.class);
        iShuts.forEach(s -> s.shout());

        Iterator<IShut> iterator = iShuts.iterator();

        while(iterator.hasNext()) {
            IShut next = iterator.next();
            if (next instanceof Dog) {
                next.shout();
            }
        }
    }
}