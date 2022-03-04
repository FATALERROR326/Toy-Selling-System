package com.company.Client;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        runSocket(new RequstEntity("query", "Tux"));
    }
    public static String runSocket(RequstEntity requst) throws IOException {
//        final CountDownLatch CDL = new CountDownLatch(5);
        boolean flag = true;
        String result = "";

        try (Socket socket = new Socket("127.0.0.1", 8088)) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader buf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (flag) {
                JSONObject json = new JSONObject();
                json.put("Method", "query");
                json.put("toyName", "Tux");
                oos.writeObject(json);
                oos.flush();
                result = buf.readLine();
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
