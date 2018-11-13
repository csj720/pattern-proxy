package com.csj.pattern.staticproxy;

import com.csj.pattern.Person;
import com.csj.pattern.Target;

public class StaticProxyTest {
    public static void main(String[] args) {
        Person proxyTarget = new ProxyTarget(new Target());
        proxyTarget.rentingHouse();
    }
}
