package com.company.utils;

import com.company.Products.Stock;

import java.util.ArrayList;
import java.util.List;

public class MyThreadPool {
    public Stock stock;
    private List<Thread> threadList;
    private int CORE_THREAD_NUM;
    //The blocking queue based on the producer-consumer mode
    private MyBlockingQueue<Runnable> workingQueue;

    public MyThreadPool(Stock stock, int CORE_THREAD_NUM, MyBlockingQueue<Runnable> workingQueue) {
        this.stock = stock;
        this.threadList = new ArrayList<>();
        this.CORE_THREAD_NUM = CORE_THREAD_NUM;
        this.workingQueue = workingQueue;

    }
    public void start(){
        for(int i=0; i<CORE_THREAD_NUM; i++){
            //Thread t = new CoreThread(stock, workingQueue);
            Thread t = new CoreThread(workingQueue);
            threadList.add(t);
            t.start();
        }

    }
    public void end(){
        for(Thread t : threadList){
            t.interrupt();
        }
    }
    public void execute(Runnable runnable) throws InterruptedException {
        workingQueue.put(runnable);
    }


}

