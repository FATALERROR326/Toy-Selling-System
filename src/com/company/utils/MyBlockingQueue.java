package com.company.utils;

import com.oracle.tools.packager.Log;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * Construct blocking queue for storing sockets waiting for pick-up from threads.
 * @param <T>
 */
public class MyBlockingQueue<T> {
    private final Logger logger = Logger.getLogger(MyBlockingQueue.class.getName());
    private int SIZE;
    private Deque<T> deque;
    private Lock lock;
    Condition read;
    Condition write;

    /**
     * Initialize
     * @param size the size of queue
     */
    public MyBlockingQueue(int size) {
        SIZE = size;
        deque = new ArrayDeque<T>();
        lock = new ReentrantLock();
        read = lock.newCondition();
        write = lock.newCondition();
    }

    /**
     * Get the first element of queue
     * @return general type (client socket here)
     */
    public T take(){
        lock.lock();
        T t = null;
        try{
            while(deque.size() == 0){
                read.wait();
            }
            t = deque.pollFirst();
            write.signalAll();
        } catch (Exception e){
            logger.warning("Lock error");
        } finally{
            lock.unlock();
            return t;
        }
    }

    /**
     * Put element in rear of queue
     */
    public void put(T t){
        lock.lock();
        try{
            while(deque.size() == SIZE){
                write.await();
            }
            deque.addLast(t);
            read.signalAll();
        } catch (Exception e){
            logger.warning("Lock error");
        } finally {
            lock.unlock();
        }
    }
}
