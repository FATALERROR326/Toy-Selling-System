package net.gRPCServer;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import net.Products.*;
import net.service.ToyServiceImpl;

import java.io.IOException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class GrpcServer {
    public final static int DEFAULT_THREADS = 3;
    static int MAX_THREADS;
    public static void main(String[] args) throws IOException, InterruptedException {
        if(args.length == 0){
            MAX_THREADS = DEFAULT_THREADS;
        }else{
            MAX_THREADS = Integer.parseInt(args[0]);
        }
        Stock stock = Stock.getInstance();
        stock.register(new Tux("Tux", (float) 29.9), 100);
        stock.register(new Whale("Whale", (float) 39.9), 100);
        stock.register(new Elephant("Elephant", (float) 49.9), 5);
        stock.register(new Bird("Bird", (float) 19.9), 0);
        Server server = ServerBuilder
                .forPort(8088)
                .addService(new ToyServiceImpl())
                .executor(new ScheduledThreadPoolExecutor(MAX_THREADS))
                .build();
        server.start();

        System.out.println("Server started on " + server.getPort());
        server.awaitTermination();
    }

}
