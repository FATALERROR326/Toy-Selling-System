package com.company.utils;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<T> {
    private final int size;
    private final Deque<T> deque;
    private final Lock lock;
    Condition read;
    Condition write;

    /**
     * Lazy loading blocking queue
     * @param size
     */
    public MyBlockingQueue(int size) {
        this.size = size;
        deque = new ArrayDeque<>();
        lock = new ReentrantLock();
        read = lock.newCondition();
        write = lock.newCondition();
    }

    public T take() throws InterruptedException {
        //Take a task into the queue if queue is not empty or the thread will be blocked
        lock.lockInterruptibly();
        T t = null;
        try{
            while(deque.isEmpty()){
                read.await();
            }
            t = deque.pollFirst();
            write.signalAll();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            lock.unlock();
        }
        return t;
    }

    public void put(T t) throws InterruptedException {
        //Put a task into the queue if queue is not full or the thread will be blocked
        lock.lock();
        try{
            while(deque.size() == size){
                write.await();
            }
            deque.addLast(t);
            read.signalAll();
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
