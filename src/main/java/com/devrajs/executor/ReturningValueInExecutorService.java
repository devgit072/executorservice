package com.devrajs.executor;

import com.devrajs.executor.common.LoopTask;
import com.devrajs.executor.common.ReturnValueInCallableMethod;
import com.devrajs.executor.common.ReturningTaskResultInCallable;
import com.devrajs.executor.common.TaskResult;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ReturningValueInExecutorService {
    //value can be returned in executor service through callable method call only NOT by runnable
    /*
    Following method has one disadvantage: if you submitted 4 tasks and if tasks 4 completed before task1 , then
    there is no way to know that(ofcourse if you printing something in call method then seeing by logs else not)
    But there is one class ComepletionExecutorService which will store the sequence of tasks in same order as tasks are completed
     */
    public static void returnValueInCallable()
    {
        ReturnValueInCallableMethod task1 = new ReturnValueInCallableMethod(2,3,2000);
        ReturnValueInCallableMethod task2 = new ReturnValueInCallableMethod(12,13,1000);
        ReturnValueInCallableMethod task3 = new ReturnValueInCallableMethod(18,83,500);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> result = executorService.submit(task1);
        Future<Integer> result1 = executorService.submit(task2);
        Future<Integer> result2 = executorService.submit(task3);

        try {
            System.out.println("Result1 : " + result.get());
            System.out.println("Result1 success: " + result.isDone());
            System.out.println("Result2 : " + result1.get());
            System.out.println("Result2 success: " + result1.isDone());
            System.out.println("Result3 : " + result2.get());
            System.out.println("Result3 success: " + result2.isDone());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("main thread ends here...");
        executorService.shutdown();
    }

    public static void callableMethodThrowingException() throws ExecutionException, InterruptedException {
        ReturnValueInCallableMethod returnValueInCallableMethod = new ReturnValueInCallableMethod(-3, 4, 1000);
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> future = executorService.submit(returnValueInCallableMethod);//exception will not come in this line.
        //Exception will be stored silently in Future future result and will be thrown only when
        System.out.println("Result:" + future.get());
        System.out.println("Tasks success:" + future.isDone());
        System.out.println("Completed");
    }
    public static void getFutureFromRunnable() throws ExecutionException, InterruptedException {
        LoopTask loopTask = new LoopTask();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(loopTask);
        System.out.println("Result:" + future.get());// it will print null, since in runnable we can't return anything
        System.out.println("Task success: " + future.isDone());//it will print true since no unexpected happened
    }

    /*
    completionException will store the Future in same sequence as it completed. So we can know which tasks completed first
    It will be helpful in debugging also...cheers!!
     */
    /*
    Well, this method too has one drawback, it will return you thw result in same sequence as it completed but how you will
    know that which tasks has returned this result?
     */
    public static void printTasksInSameOrderAsItCompleted() throws InterruptedException, ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
        CompletionService<Integer> completionException = new ExecutorCompletionService<Integer>(service);
        completionException.submit(new ReturnValueInCallableMethod(12,34,2000));
        completionException.submit(new ReturnValueInCallableMethod(12,34,1000));
        completionException.submit(new ReturnValueInCallableMethod(92,7,500));

        for(int i=0;i<3;i++)
        {
            System.out.println("Result: " + completionException.take().get());
        }
        service.shutdown();
    }
    /*
    This method will return you the taskId and result calculated by that tasks, so you can print both tasks and results as well
    In fact you can get the taskId and Result as from from Future variable....cheers!!!
     */
    public static void printTasksInSameOrderAsItCompletedWithTaskIdAndResultsBoth() throws InterruptedException,
            ExecutionException {
        ExecutorService service = Executors.newCachedThreadPool();
        CompletionService<TaskResult<String, Integer>> completionService =
                new ExecutorCompletionService<TaskResult<String, Integer>>(service);
        completionService.submit(new ReturningTaskResultInCallable(12,34,2000));
        completionService.submit(new ReturningTaskResultInCallable(22,314,1000));
        completionService.submit(new ReturningTaskResultInCallable(152,342,500));
        completionService.submit(new ReturningTaskResultInCallable(121,374,1500));

        for(int i=0;i<4;i++)
        {
            System.out.println(completionService.take().get().toString());
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        printTasksInSameOrderAsItCompletedWithTaskIdAndResultsBoth();
    }
}
