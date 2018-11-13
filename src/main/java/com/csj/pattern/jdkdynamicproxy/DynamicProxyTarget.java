package com.csj.pattern.jdkdynamicproxy;

import com.csj.pattern.Person;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTarget implements InvocationHandler {
    private Person target;

    public Object getInstance(Person person) throws Exception{
        this.target = person;
        Class<?> clazz = target.getClass();
        return Proxy.newProxyInstance(clazz.getClassLoader(), clazz.getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是房屋中介：给你介绍房子");
        method.invoke(this.target, args);
        System.out.println("收取中介费");
        return null;
    }
}
