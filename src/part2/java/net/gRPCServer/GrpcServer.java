package net.gRPCServer;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import net.Products.*;
import net.service.ToyServiceImpl;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class GrpcServer {
    private static final int DEFAULT_THREADS = 3;
    private static final int DEFAULT_PORT = 7077;
    static int MAX_THREADS;
    //args[0]: max core thread size of the dynamic threadpool
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length == 0){
            MAX_THREADS = DEFAULT_THREADS;
        }else{
            MAX_THREADS = Integer.parseInt(args[0]);
        }
        Stock stock = Stock.getInstance();
        stock.register(new Tux("Tux", (float) 29.9), 5);
        stock.register(new Whale("Whale", (float) 39.9), 5);
        stock.register(new Elephant("Elephant", (float) 49.9), 5);
        stock.register(new Bird("Bird", (float) 19.9), 5);
        Server server = ServerBuilder
                .forPort(DEFAULT_PORT)
                .addService(new ToyServiceImpl())
                .executor(new ScheduledThreadPoolExecutor(MAX_THREADS))
                .build();
        server.start();

        System.out.println("Server started on " + server.getPort() + " with maximum thread pool size of " + MAX_THREADS);
        server.awaitTermination();
    }

}
