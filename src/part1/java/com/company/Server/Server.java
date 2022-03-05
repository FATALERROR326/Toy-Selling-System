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
    private static final int PORT_NUM_DEFAULT = 8088;
    public static final int CORE_SIZE_DEFAULT = 3;

    public Stock stock;
    public ServerSocket serverSocket;
    public Server(int portNum) throws IOException {
        serverSocket = new ServerSocket(portNum);
        stock = new Stock();
        stock.register(new Tux("Tux", (float) 29.9), 100);
        stock.register(new Whale("Whale", (float) 39.9), 100);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        int port = PORT_NUM_DEFAULT;
        int coreSize = CORE_SIZE_DEFAULT;
        if(args.length == 2) {
            port = Integer.parseInt(args[1]);
        }
        if(args.length == 1) {
            coreSize = Integer.parseInt(args[0]);
        }
        Server server = new Server(port);
        ServerSocket serverSocket = server.serverSocket;
        MyThreadPool threadPool = new MyThreadPool(
                server.stock,
                coreSize,
                new MyBlockingQueue<>(20)
        );
        threadPool.start();
        logger.info("Server started on port " + port + " with maximum " + coreSize + " threads.");
        while(true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Receive the socket from: " + clientSocket.getInetAddress());
            //For every accepted socket, put it into the blocking queue with the runnable task
            threadPool.execute(new Handler(clientSocket, server.stock));
        }

    }
}
