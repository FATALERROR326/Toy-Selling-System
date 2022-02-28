package com.company.utils;

import com.company.Products.Stock;

public class CoreThread extends Thread implements MyThread{
    private Stock stock;
    private MyBlockingQueue<Runnable> workingQueue;

    public CoreThread(Stock stock, MyBlockingQueue<Runnable> workingQueue) {
        this.stock = stock;
        this.workingQueue = workingQueue;
    }

    @Override
    public String Query(String toyName) {
        int remain = stock.getStock(toyName);

        if(remain == -1){
            return "No such product called " + toyName + " in our catalog.";
        }
        else{
            double price = stock.getPrice(toyName);
            return "There are "
                    + remain + toyName
                    + " left in the stock."
                    + " $" + price + " for each.";
        }
    }

    @Override
    public String Buy(String toyName) {
        return null;
    }

    @Override
    public void run(){
        while(true){
            Runnable handler = workingQueue.take();
            handler.run();
        }
    }
}
