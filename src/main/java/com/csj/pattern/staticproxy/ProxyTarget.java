package com.csj.pattern.staticproxy;

import com.csj.pattern.Person;

/**
 * 代理对象
 */
public class ProxyTarget implements Person {

    private Person proxyPerson;

    public ProxyTarget(Person person) {
        this.proxyPerson = person;
    }

    public void rentingHouse() {
        System.out.println("帮忙看房子");
        this.proxyPerson.rentingHouse();
    }
}
