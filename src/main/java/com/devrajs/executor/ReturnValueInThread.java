package com.devrajs.executor;

import com.devrajs.executor.common.ThreadReturningValue;
import com.devrajs.executor.common.ThreadReturningValueCorrect;

/*
There is no language support in java where we can return values in thread.
Howevere there is workaround, in class which is implementing runnable can have a method which return value
 */
public class ReturnValueInThread {

    public static void retrunValueFromThread()
    {
        ThreadReturningValue trv1 = new ThreadReturningValue(12,30,"Thread1");
        ThreadReturningValue trv2 = new ThreadReturningValue(20,80,"Thread2");
        Thread thread1 = new Thread(trv1);
        Thread thread2 = new Thread(trv2);

        thread1.start();
        thread2.start();

        // Following two lines will print 0 both and sum is calculated.
        // Why? because main thread is executed and completed before both thread run method got executed
        System.out.println(trv1.getSum());
        System.out.println(trv2.getSum());
    }

    public static void returnValueFromThreadCorrectWay()
    {
        ThreadReturningValueCorrect trv1 = new ThreadReturningValueCorrect(12,30, "MyThread1");
        ThreadReturningValueCorrect trv2 = new ThreadReturningValueCorrect(42,80, "MyThread2");

        Thread thread = new Thread(trv1);
        Thread thread1 = new Thread(trv2);
        thread.start();
        thread1.start();
        System.out.println(trv1.getSum());
        System.out.println(trv2.getSum());
    }

    public static void main(String[] args) {
        returnValueFromThreadCorrectWay();
    }
}
