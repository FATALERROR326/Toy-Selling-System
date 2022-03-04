package com.company.utils;

import com.alibaba.fastjson.JSONObject;
import com.company.Products.Stock;

import java.io.*;
import java.net.Socket;


public class Handler implements Runnable {
    private final Socket client;
    private final Stock stock;
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
                //Get json object from client socket input
                JSONObject json = (JSONObject) ois.readObject();
                if(json == null || json.isEmpty()) flag = false;
                else{
                    String method = json.getString("Method");
                    String toyName = json.getString("toyName");
                    if(method.equals("query")){
                        Response response = query(toyName);
                        if(response.getState() != 1) out.println(response.getState());
                        else out.println(response.getPrice());
                    }
                    else if(method.equals("buy")){
                        Response response = buy(toyName);
                        out.println(response.getState());
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        excute(client);
    }
    public Response buy(String toyName){
        return stock.buy(toyName);
    }
    public Response query(String toyName){
        return stock.query(toyName);
    }
}
