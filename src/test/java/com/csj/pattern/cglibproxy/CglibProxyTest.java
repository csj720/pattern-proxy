package com.csj.pattern.cglibproxy;

import com.csj.pattern.cglib.CglibProxy;
import com.csj.pattern.cglib.Target;

public class CglibProxyTest {
    public static void main(String[] args) {
        Target target = (Target) new CglibProxy().getInstance(Target.class);
        target.rentingHouse();
    }
}
