package com.example.webflux.demo.spis.impl;

import com.example.webflux.demo.spis.interfaces.IShut;

/**
 * @project_name: spring-boot-webflux
 * @date: 2020/5/13 - 0:35
 * @author: Mr_Bangb
 */
public class Cat implements IShut {
    @Override
    public void shout() {
        System.out.println("miao miao");
    }
}