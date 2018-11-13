package com.csj.pattern.custom;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public interface CSJInvocationHandler{
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
