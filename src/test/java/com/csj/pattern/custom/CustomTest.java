package com.csj.pattern.custom;

import com.csj.pattern.Person;
import com.csj.pattern.Target;

public class CustomTest {
    public static void main(String[] args) {
        Person obj = (Person) new Custom().getInstance(new Target());
        System.out.println(obj.getClass());
        obj.rentingHouse();
    }
}
