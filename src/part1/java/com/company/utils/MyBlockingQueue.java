package com.company.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    private int SIZE;
    private Deque<T> deque;
    private Lock lock;
    Condition read;
    Condition write;

    public MyBlockingQueue(int size) {
        SIZE = size;
        deque = new ArrayDeque<T>();
        lock = new ReentrantLock();
        read = lock.newCondition();
        write = lock.newCondition();
    }

    public T take() throws InterruptedException {
        //Take a task into the queue if queue is not empty or the thread will be blocked
        lock.lockInterruptibly();
        T t = null;
        try{
            while(deque.size() == 0){
                read.await();
            }
            t = deque.pollFirst();
            write.signalAll();
        }catch (Exception e){

        }finally{
            lock.unlock();
            return t;
        }
    }
    public void put(T t) throws InterruptedException {
        //Put a task into the queue if queue is not full or the thread will be blocked
        lock.lock();
        try{
            while(deque.size() == SIZE){
                write.await();
            }
            deque.addLast(t);
            read.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
