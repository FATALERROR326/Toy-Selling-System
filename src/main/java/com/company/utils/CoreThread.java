package com.company.utils;

import com.company.Products.Stock;

public class CoreThread extends Thread{
    private MyBlockingQueue<Runnable> workingQueue;

    public CoreThread(MyBlockingQueue<Runnable> workingQueue) {
        this.workingQueue = workingQueue;
    }



    @Override
    public void run(){
        while(true){
            Runnable handler = null;
            try {
                handler = workingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            handler.run();
        }
    }
}
