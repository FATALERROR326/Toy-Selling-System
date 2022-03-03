package com.company.Client;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;

public class Client {
    public static void main(String[] args) throws IOException, InterruptedException {
        final CountDownLatch CDL = new CountDownLatch(5);
        Socket socket = new Socket("127.0.0.1", 8088);

        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        boolean flag = true;
        while(flag){

            for(int i=0; i<5; i++){
                JSONObject json = new JSONObject();
                json.put("Method", "query");
                json.put("toyName", "Tux");
                oos.writeObject(json);
                oos.flush();
                String result = buf.readLine();
                System.out.println(result);
            }


            flag = false;
        }

        socket.close();
    }

}