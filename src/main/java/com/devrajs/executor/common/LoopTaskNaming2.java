package com.devrajs.executor.common;

public class LoopTaskNaming2 implements Runnable{

    @Override
    public void run() {
        //For this class threadName will be set by User or by default Thread-0 name will be set by JVM.
        String threadName = Thread.currentThread().getName();
        System.out.println("START of thread: " + threadName);
        for (int i = 0; i < 10; i++) {
            System.out.println("In " + threadName + " , value of i=" + i);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("END of thread: " + threadName);
    }
}
