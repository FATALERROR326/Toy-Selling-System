package com.company.Server;

import com.company.Products.Stock;
import com.company.Products.Tux;
import com.company.Products.Whale;
import com.company.utils.Handler;
import com.company.utils.MyBlockingQueue;
import com.company.utils.MyThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class.getName());
    public Stock stock;
    public static final int CORE_SIZE = 3;
    public ServerSocket serverSocket;
    public Server() throws IOException {
        serverSocket = new ServerSocket(8088);
        stock = new Stock();
        stock.register(new Tux("Tux", (float) 29.9), 100);
        stock.register(new Whale("Whale", (float) 39.9), 100);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = new Server();
        ServerSocket serverSocket = server.serverSocket;
        MyThreadPool threadPool = new MyThreadPool(
                server.stock,
                CORE_SIZE,
                new MyBlockingQueue<Runnable>(20)
        );
        threadPool.start();
        logger.info("Server started.");
        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Receive the socket from: " + clientSocket.getInetAddress());
            //For every accepted socket, put it into the blocking queue with the runnable task
            threadPool.execute(new Handler(clientSocket, server.stock));
        }

    }
}
