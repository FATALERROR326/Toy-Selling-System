package com.company.Client;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8088);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        boolean flag = true;
        while(flag){
            JSONObject json = new JSONObject();
            json.put("Method", "query");
            json.put("toyName", "Tux");
            oos.write(json.toString().getBytes());
        }
    }
}
