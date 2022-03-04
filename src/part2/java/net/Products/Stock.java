package net.Products;


import net.utils.Response;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This class maintains all the data accesses about toys
 */
public class Stock {
    private ConcurrentHashMap<String, Integer> dic; // used to store amount in stock
    private HashMap<String, Toy> list; // used to store information of toy
    private int state;// Record how many threads are trying to read
    public Stock(){
        dic = new ConcurrentHashMap<>();
        list = new HashMap<>();
        state = 0;
    }

    //Singleton mode to get instance
    public static volatile Stock instance = null;

    public static Stock getInstance(){
        if(instance == null){
            synchronized (Stock.class){
                if(instance == null){
                    instance = new Stock();
                }
            }
        }
        return instance;
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
    public Response query(String toyName){
        if(!dic.containsKey(toyName)) return new Response(-1);

        if(dic.get(toyName) <= 0) return new Response(0, getPrice(toyName), 0);
        else return new Response(1, getPrice(toyName), dic.get(toyName));
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
     * @param toyName given toy name
     * @return mark of having bought
     */
    public Response buy(String toyName){
        if(!dic.containsKey(toyName)) return new Response(-1);

        if(dic.get(toyName) <= 0) return new Response(0);
        dic.put(toyName, dic.get(toyName)-1);
        return new Response(1);
    }
}
