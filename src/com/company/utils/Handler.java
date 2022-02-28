package com.company.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Handler implements Runnable {
    private Socket client;
    public Handler(Socket client){
        this.client = client;
    }
    public void excute(Socket client){
        try{
            //Output stream to client socket
            PrintStream out = new PrintStream(client.getOutputStream());
            //Input stream from client socket
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
            boolean flag = true;

            while(flag){

            }
        }catch (Exception e){

        }
    }
    @Override
    public void run() {
        excute(client);
    }
}
