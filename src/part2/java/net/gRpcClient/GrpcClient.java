package net.gRpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.grpc.Request;
import net.grpc.Respond;
import net.grpc.ToyServiceGrpc;

public class GrpcClient{

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 8088)
                .usePlaintext()
                .build();
        ToyServiceGrpc.ToyServiceBlockingStub stub = ToyServiceGrpc.newBlockingStub(channel);
        for (int i = 0; i < 10; i++) {
            Respond response = stub.buy(Request.newBuilder().
                    setToyName("Elephant")
                    .build());
            System.out.println(response.getSuccess());
        }

//        ToyServiceOuterClass.Response response = stub.query(ToyServiceOuterClass.Request.newBuilder()
//                .setToyName("Tux")
//                .build());
//        System.out.println(response);
        channel.shutdown();
    }

}
