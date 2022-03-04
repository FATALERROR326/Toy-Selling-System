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
        Respond response = stub.query(Request.newBuilder().
                setToyName("Tux")
                .build());
//        ToyServiceOuterClass.Response response = stub.query(ToyServiceOuterClass.Request.newBuilder()
//                .setToyName("Tux")
//                .build());
//        System.out.println(response);
        channel.shutdown();
    }

}
