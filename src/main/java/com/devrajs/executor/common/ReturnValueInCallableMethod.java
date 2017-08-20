package com.devrajs.executor.common;

import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.concurrent.Callable;

public class ReturnValueInCallableMethod implements Callable<Integer> {

    private int a, b, sleepTime;
    private static int count=0;
    private String instance="Thread-";

    public ReturnValueInCallableMethod(int a, int b, int sleepTime) {
        this.a = a;
        this.b = b;
        this.sleepTime = sleepTime;
        instance = instance.concat(++count+"");
    }

    @Override
    public Integer call() throws Exception{
        System.out.println("Running thread: " + instance);
        Thread.sleep(sleepTime);
        System.out.println("Completed thread: " + instance);
        //lets show demo that call can throw exception as well if required
        if(a<0) throw new InvalidArgument("Negative value not allowed : " + a);
        return a+b;
    }
}
