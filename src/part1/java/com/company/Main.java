package com.company;

import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final int PORT = 7077;
    public static void main(String[] args) throws IOException {
	    try {
            System.out.println("waiting client...");
            ServerSocket serverSocket = new ServerSocket(PORT);
            Socket clientSocket = serverSocket.accept();
            System.out.println("client online");
            while(true) {
                try {
                    ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                    JSONObject msg = (JSONObject) ois.readObject();

                    System.out.println(msg.get("Method"));
                } catch (IOException e) {
                    System.out.println("read/write error");
                    e.printStackTrace();
                } finally {
                    serverSocket.close();
                    clientSocket.close();
                    System.out.println("server shutdown");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("port in use!");
            e.printStackTrace();
        }
    }
}
