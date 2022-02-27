package com.company.Products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Stock {
    private HashMap<String, Integer> dic;
    private HashMap<String, Toy> list;
    //Try to build a Read and Write lock. Only block writing operations when reading.
    //Block either reading or writing when writing
    private ReentrantLock lock;
    private int state;// Record how many threads are trying to read
    public Stock(){
        dic = new HashMap<>();
        list = new HashMap<>();
        lock = new ReentrantLock();
        state = 0;
    }
    public void register(Toy toy, int stock){
        dic.put(toy.getName(), stock);
        list.put(toy.getName(), toy);
    }
    public int getStock(String toyName){
        if(!dic.containsKey(toyName)) return -1;
        else return dic.get(toyName);
    }
    public double getPrice(String toyName){
        return list.get(toyName).getPrice();
    }
//    public boolean buy(String toyName){
//
//    }
}
