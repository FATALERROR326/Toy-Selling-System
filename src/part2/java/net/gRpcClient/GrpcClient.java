package net.gRpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.grpc.Request;
import net.grpc.Respond;
import net.grpc.ToyServiceGrpc;

import java.util.concurrent.CountDownLatch;

public class GrpcClient{
    static String host;
    static int port;
//    static final String DEFAULT_METHOD = "query";
//    static final String DEFAULT_TOYNAME = "Tux";
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 8088;
    //args[0]: host args[1]: port
    public static void main(String[] args) throws InterruptedException {
        if(args.length == 0){
            host = DEFAULT_HOST;
            port = DEFAULT_PORT;

        }else{
            host = args[0];
            port = Integer.parseInt(args[1]);
        }

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8088)
                .usePlaintext()
                .build();
        ToyServiceGrpc.ToyServiceBlockingStub stub = ToyServiceGrpc.newBlockingStub(channel);
        CountDownLatch CDL = new CountDownLatch(10);
        send(stub, CDL);
        CDL.await();

        channel.shutdown();
    }
    public static void send(ToyServiceGrpc.ToyServiceBlockingStub stub, CountDownLatch CDL){
        //Send requests concurrently
        for (int i = 0; i < 250; i++) {
            //modify the method name here stub.buy or stub.query
            if(i % 2 == 1){
                new Thread(()->{
                    Respond response = stub.buy(Request.newBuilder()
                            .setToyName("Tux")
                            .build());
                    System.out.println(response.getSuccess());
                    CDL.countDown();
                }).start();
            }
            if(i % 2 == 0){
                new Thread(()->{
                    Respond response = stub.query(Request.newBuilder()
                            .setToyName("Tux")
                            .build());
                    System.out.println("Item price: "+response.getPrice()+", Stock: "+response.getStock());
                    CDL.countDown();
                }).start();
            }
        }
    }


}
