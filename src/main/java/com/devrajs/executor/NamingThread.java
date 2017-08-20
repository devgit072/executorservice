package com.devrajs.executor;

import com.devrajs.executor.common.LoopTaskNaming1;
import com.devrajs.executor.common.LoopTaskNaming2;
import com.devrajs.executor.common.NameThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NamingThread {
    public static void fixedThreadPoolWithName1()
    {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new LoopTaskNaming1());
        service.submit(new LoopTaskNaming1());
        service.submit(new LoopTaskNaming1());
        service.submit(new LoopTaskNaming1());
        service.submit(new LoopTaskNaming1());

        service.shutdown();
    }
    public static void bestWayToNameThread()
    {
        /*This is two best way of setting thread name.
           1. Through constructor
           2. Through thread.setName()
        */
        Thread thread = new Thread(new LoopTaskNaming2(), "MyThread1");
        thread.start();
        Thread thread1 = new Thread(new LoopTaskNaming2());
        thread1.setName("MyThread2");
        thread1.start();
    }
    ////////////////////////////////////////////////////////////////////////////////
    /*
    If you will not name thread in executor service, than by default thread name will be given as pool-n-thread-m
     */
    public static void defaultnNameThreadInExecutorService()
    {
        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new LoopTaskNaming2());
        service.submit(new LoopTaskNaming2());
        service.submit(new LoopTaskNaming2());//All above three will print like this : pool-1-thread-[1,2,3]

        ExecutorService service1 = Executors.newFixedThreadPool(3);
        service1.submit(new LoopTaskNaming2());
        service1.submit(new LoopTaskNaming2());
        service1.submit(new LoopTaskNaming2());//All above three will print like this : pool-2-thread-[1,2,3]

        service.shutdown();
        service1.shutdown();
    }
    /*
    There is no straightforward way to name the thread like constructor or setName etc.
    But there is workaround . We can implement ThreadFactory and name the thread and pass it to executor service
     */

    /*
    We have created a class implementing ThreadFactory where we have set name.
     */
    public static void nameThreadByThreadFactory()
    {
        ExecutorService service = Executors.newFixedThreadPool(2, new NameThreadFactory());
        service.submit(new LoopTaskNaming2());
        service.submit(new LoopTaskNaming2());
        service.submit(new LoopTaskNaming2());

        service.shutdown();
    }

    public static void main(String[] args) {
        nameThreadByThreadFactory();
    }

}
