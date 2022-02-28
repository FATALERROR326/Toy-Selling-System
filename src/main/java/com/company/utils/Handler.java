package com.company.utils;

import com.alibaba.fastjson.JSONObject;
import com.company.Products.Stock;

import java.io.*;
import java.net.Socket;


public class Handler implements Runnable {
    private Socket client;
    private Stock stock;
    public Handler(Socket client, Stock stock){
        this.stock = stock;
        this.client = client;
    }
    public void excute(Socket client){
        try{
            //Output stream to client socket
            PrintStream out = new PrintStream(client.getOutputStream());
            //Input stream from client socket
            ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
            boolean flag = true;

            while(flag){
                JSONObject json = (JSONObject) ois.readObject();
                if(json == null || json.isEmpty()) flag = false;
                else{
                    double result = 0.0;
                    String method = json.getString("Method"),
                            toyName = json.getString("toyName");
                    if(method.equals("query")){
                        result = (double) query(toyName);
                    }
                    else if(method.equals("buy")){
                        result = (double) buy(toyName);
                    }
                    else if(method.equals("getPrice")){
                        result = getPrice(toyName);
                    }
                    out.println("From Server: "+result);
                }


            }
        }catch (Exception e){

        }
    }
    @Override
    public void run() {
        excute(client);
    }
    public int buy(String toyName){
        return stock.buy(toyName);
    }
    public int query(String toyName){
        return stock.getStock(toyName);
    }
    public double getPrice(String toyName){
        return stock.getPrice(toyName);
    }
}
