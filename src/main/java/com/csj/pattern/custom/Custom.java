package com.csj.pattern.custom;

import com.csj.pattern.Person;

import java.lang.reflect.Method;

public class Custom implements CSJInvocationHandler {

    private Person target;

    public Object getInstance(Person target){
        this.target = target;
        Class clazz = target.getClass();
        //用来生成一个新的对象（字节码重组来实现）
        return CSJProxy.newProxyInstance(new CSJClassLoader(), clazz.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("我是房屋中介：给你介绍房子");
        method.invoke(this.target, args);
        System.out.println("收取中介费");
        return null;
    }
}
