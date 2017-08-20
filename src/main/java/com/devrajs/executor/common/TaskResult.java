package com.devrajs.executor.common;

public class TaskResult<T,V> {
    private T t;
    private V v;

    public T getT() {
        return t;
    }

    public V getV() {
        return v;
    }

    @Override
    public String toString() {
        return "TaskResult{" +
                "t=" + t +
                ", v=" + v +
                '}';
    }

    public TaskResult(T t, V v)
    {
        this.t=t;
        this.v=v;
    }
}
