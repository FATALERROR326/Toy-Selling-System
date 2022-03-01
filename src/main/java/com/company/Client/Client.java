package com.company.Client;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 7077);
        JSONObject json = new JSONObject();
        json.put("Method", "query");
        json.put("toyName", "Tux");


        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(json);
            oos.flush();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        } finally {
            socket.close();
            System.out.println("client shutdown");
        }
    }
}
