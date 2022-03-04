package com.company.Products;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class maintains all the data accesses about toys
 */
public class Stock {
    private ConcurrentHashMap<String, Integer> dic; // used to store amount in stock
    private HashMap<String, Toy> list; // used to store information of toy
    //Try to build a Read and Write lock. Only block writing operations when reading.
    //Block either reading or writing when writing
    private ReentrantLock lock; //TODO: is this necessary?
    private int state;// Record how many threads are trying to read
    public Stock(){
        dic = new ConcurrentHashMap<>();
        list = new HashMap<>();
        lock = new ReentrantLock();
        state = 0;
    }

    /**
     * Register a new toy
     * @param toy details of toy
     * @param stock stock to renew
     */
    public void register(Toy toy, int stock){
        dic.put(toy.getName(), stock);
        list.put(toy.getName(), toy);
    }

    /**
     * Get current toy number, -1 if not existed
     * @param toyName the name Key of toy
     * @return current toy number, -1 if not existed
     */
    public int getStock(String toyName){
        if(!dic.containsKey(toyName)) return -1;
        else return dic.get(toyName);
    }

    /**
     * Get the price of given toy name
     * @param toyName given toy name
     * @return price of it
     */
    public double getPrice(String toyName){
        return list.get(toyName).getPrice();
    }

    /**
     * TODO: make sure if it is necessary
     * @param toyName given toy name
     * @return mark of having bought
     */
    public int buy(String toyName){
        if(!dic.containsKey(toyName)) return -1;
        dic.put(toyName, dic.get(toyName)-1);
        if(dic.get(toyName) == 0) dic.remove(toyName);
        return 1;
    }
}
