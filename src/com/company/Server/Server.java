package com.company.Server;

import com.company.Products.Stock;
import com.company.Products.Toy;
import com.company.Products.Tux;
import com.company.Products.Whale;
import com.company.utils.MyBlockingQueue;
import com.company.utils.MyThreadPool;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;

public class Server {
    public Stock stock;
    public MyThreadPool threadPool;
    public static final int CORE_SIZE = 3;
    public ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket = new ServerSocket(8080);
        stock = new Stock();
        threadPool = new MyThreadPool(
                stock,
                CORE_SIZE,
                new MyBlockingQueue<String>(20)
        );
        stock.register(new Tux("Tux", (float) 29.9), 100);
        stock.register(new Whale("Whale", (float) 39.9), 100);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        ServerSocket serverSocket = server.serverSocket;
        while(true){
            Socket clientSocket = serverSocket.accept();
            InputStreamReader isr = new InputStreamReader(clientSocket.getInputStream());

        }

    }
}
