package com.company.utils;

import com.alibaba.fastjson.JSONObject;
import com.company.Products.Stock;
import net.utils.Response;

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
                    int result = 0;
                    String method = json.getString("Method"),
                            toyName = json.getString("toyName");
                    if(method.equals("query")){
                        Response response = query(toyName);
                        if(response.state != 1) out.println("From Server: "+ response.state);
                        else out.println("From Server: "+ response.price);

                    }
                    else if(method.equals("buy")){
                        Response response = buy(toyName);
                        out.println("From Server: "+response.state);
                    }

                }


            }
        }catch (Exception e){

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
