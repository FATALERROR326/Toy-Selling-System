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

    public T take(){
        lock.lock();
        T t = null;
        try{
            while(deque.size() == 0){
                read.wait();
            }
            t = deque.pollFirst();
            write.signalAll();
        }catch (Exception e){

        }finally{
            lock.unlock();
            return t;
        }
    }
    public void put(T t){
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
