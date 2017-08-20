package com.devrajs.executor.common;

public class ThreadReturningValueCorrect implements Runnable {

    private int a, b, sum;
    private volatile boolean isDone = false;

    public ThreadReturningValueCorrect(int a, int b, String threadName) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Start of thread:" + threadName);
        sum = a + b;
        System.out.println("End of thread:" + threadName);
        isDone = true;
        synchronized (this) {
            System.out.println("Synchronized in thread:"+ threadName + "-"+ this.toString());
            System.out.println(threadName + " has notified");
            notifyAll();
        }
    }

    public int getSum() {
        String threadName = Thread.currentThread().getName();
        if (!isDone) {
            synchronized (this) {
                System.out.println(threadName + " is wating in " + this.toString());
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(threadName + " is woken up");
            }
        }
        return sum;
    }
}
