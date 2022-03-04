//package net.gRPCServer;
//
//
//import io.grpc.BindableService;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import net.Products.Stock;
//import net.Products.Tux;
//import net.Products.Whale;
//import net.service.ToyServiceImpl;
//
//import java.io.IOException;
//
//public class GrpcServer {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        Server server = ServerBuilder.forPort(8088).addService((BindableService) new ToyServiceImpl()).build();
//        Stock stock = new Stock();
//        stock.register(new Tux("Tux", (float) 29.9), 100);
//        stock.register(new Whale("Whale", (float) 39.9), 100);
//        server.start();
//
//        System.out.println("Server started on " + server.getPort());
//        server.awaitTermination();
//    }
//
//}
