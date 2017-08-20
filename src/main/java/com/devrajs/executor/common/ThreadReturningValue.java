package com.devrajs.executor.common;

public class ThreadReturningValue implements Runnable {

    private int a, b, sum;
    private String threadName;

    public ThreadReturningValue(int a, int b, String threadName)
    {
        this.a=a;
        this.b=b;
        this.threadName=threadName;
    }

    @Override
    public void run()
    {
        System.out.println("Start of thread:" + threadName);
        sum = a+b;
        System.out.println("End of thread:" + threadName);
    }

    public int getSum()
    {
        return sum;
    }
}
