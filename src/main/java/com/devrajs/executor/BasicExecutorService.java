package com.devrajs.executor;

import com.devrajs.executor.common.LoopTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BasicExecutorService {

    /*
    This is fixed thread pool class, at start we will tell executor service that instantiate pool with n number of
    threads.
    If more than n tasks are submitted then, extra tasks have to wait to get any thread to work done, till one of the
     thread becomes free.
     */
    public static void fixedThreadPool() {

        ExecutorService service = Executors.newFixedThreadPool(3);
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        /*
        First three thread will try to finish and then executor service will release the thread and allocate new tasks
         */
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
        //U must call shutdown else, there will be memory leak and also program will not shutdown
        //comment below line and u will see program will not end.
        service.shutdown();
        System.out.println(service.isShutdown());
        System.out.println(service.isTerminated());
    }

    /*
    The difference between fixedThread pool and cached theradpool is that, in fixed threadpool, there will be fixed
    number of threads.
    At any time only n tasks will be executed.Extra tasks submitted while n threads are running, will have to wait.

    In cached threadpool, we will start threadpool with n number of threads, if extra tasks will be submitted than
    new thread will be created.
    Lets say we started our pool with n number of threads, and we subitted n number of tasks then all n thread are
    busy executing those n tasks and suddenly new tasks is submitted
    then one more thread will be created.
     */
    public static void cachedThreadPool() {
        ExecutorService service = Executors.newCachedThreadPool();
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        /*
        First three thread will try to finish and then executor service will release the thread and allocate new tasks
         */
        service.submit(new LoopTask());
        service.submit(new LoopTask());
        service.submit(new LoopTask());

        service.shutdown();
    }

    /*
    If we want to serialize the tasks then we should use singleThreadExecutor service
    Also singleThreadExecutor service is not thread pool , technically I mean.
     */
    public static void singleThreadExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(new LoopTask());
        executorService.submit(new LoopTask());
        executorService.submit(new LoopTask());
        executorService.shutdown();
    }

    public static void main(String[] args) {
    }
}
