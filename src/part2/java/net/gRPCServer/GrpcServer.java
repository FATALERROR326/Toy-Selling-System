package net.gRPCServer;


import io.grpc.Server;
import io.grpc.ServerBuilder;
import net.Products.*;
import net.service.ToyServiceImpl;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Stock stock = Stock.getInstance();
        stock.register(new Tux("Tux", (float) 29.9), 100);
        stock.register(new Whale("Whale", (float) 39.9), 100);
        stock.register(new Elephant("Elephant", (float) 49.9), 5);
        stock.register(new Bird("Bird", (float) 19.9), 0);
        Server server = ServerBuilder.forPort(8088).addService(new ToyServiceImpl()).build();
        server.start();

        System.out.println("Server started on " + server.getPort());
        server.awaitTermination();
    }

}
