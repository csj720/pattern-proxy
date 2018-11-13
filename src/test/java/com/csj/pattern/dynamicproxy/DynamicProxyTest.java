package com.csj.pattern.dynamicproxy;

import com.csj.pattern.Person;
import com.csj.pattern.Target;
import com.csj.pattern.jdkdynamicproxy.DynamicProxyTarget;

public class DynamicProxyTest {
    public static void main(String[] args) {
        try {
            Person person = (Person) new DynamicProxyTarget().getInstance(new Target());
            person.rentingHouse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
