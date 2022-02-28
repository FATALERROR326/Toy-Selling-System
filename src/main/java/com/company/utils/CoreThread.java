package com.company.utils;

import com.company.Products.Stock;

public class CoreThread extends Thread{
    private Stock stock;
    private MyBlockingQueue<Runnable> workingQueue;

    public CoreThread(Stock stock, MyBlockingQueue<Runnable> workingQueue) {
        this.stock = stock;
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
