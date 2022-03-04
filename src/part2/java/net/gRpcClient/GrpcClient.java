package net.gRpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.grpc.Request;
import net.grpc.Respond;
import net.grpc.ToyServiceGrpc;

public class GrpcClient{
    static String host;
    static int port;
//    static final String DEFAULT_METHOD = "query";
//    static final String DEFAULT_TOYNAME = "Tux";
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 8088;
    //args[0]: host args[1]: port
    public static void main(String[] args) {
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


        for (int i = 0; i < 10; i++) {
            //modify the method name here stub.buy or stub.query
            Respond response = stub.buy(Request.newBuilder()
                    .setToyName("Elephant")
                    .build());
            System.out.println(response.getSuccess());
        }

        channel.shutdown();
    }


}
