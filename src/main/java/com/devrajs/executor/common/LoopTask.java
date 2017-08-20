package com.devrajs.executor.common;

public class LoopTask implements Runnable {

    private int threadId;
    private static int count=0;

    public LoopTask()
    {
        threadId = ++count;
    }
    @Override
    public void run() {
        System.out.println("START of thread: " + threadId);
        for (int i = 0; i < 10; i++) {
            System.out.println("In threadId: " + threadId + " , value of i=" + i);
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("END of thread: " + threadId);
    }
}
