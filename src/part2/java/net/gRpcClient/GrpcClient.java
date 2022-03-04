package net.gRpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.grpc.ToyServiceGrpc;
import net.grpc.ToyServiceOuterClass;

public class GrpcClient{

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8088).usePlaintext().build();
        ToyServiceGrpc.ToyServiceBlockingStub stub = ToyServiceGrpc.newBlockingStub(channel);
        ToyServiceOuterClass.Response response = stub.query(ToyServiceOuterClass.Request.newBuilder()
                .setToyName("Tux")
                .build());
        System.out.println(response);
        channel.shutdown();
    }

}
