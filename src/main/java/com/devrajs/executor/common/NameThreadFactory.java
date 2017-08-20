package com.devrajs.executor.common;

import java.util.concurrent.ThreadFactory;

/*
ThreadFactory is functional interface which has one method to create thread based on your needs.
Like here we need tread where we can set the custom name.
 */
public class NameThreadFactory implements ThreadFactory {

    private static int count = 0;
    private String prefix = "Name-";

    public NameThreadFactory()
    {
        //threadName = prefix.concat(++count); this won't work
    }

    @Override
    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, prefix.concat(Integer.toString(++count)));
    }

}
