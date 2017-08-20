package com.devrajs.executor.common;

public class LoopTaskNaming1 implements Runnable{
    private int threadId;
    String threadName;
    private static int count=0;

    public LoopTaskNaming1()
    {
        threadId = ++count;
        //this is not right way of creating threads, we should take name as parameter from user who is creating thread.
        Thread.currentThread().setName("TyrionThread-"+threadId);
        threadName = Thread.currentThread().getName();
    }
    @Override
    public void run() {
        System.out.println("START of thread: " + threadName);
        for (int i = 0; i < 10; i++) {
            System.out.println("In threadId: " + threadName + " , value of i=" + i);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("END of thread: " + threadName);
    }
}
