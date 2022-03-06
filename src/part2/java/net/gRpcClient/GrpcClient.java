package net.gRpcClient;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import net.grpc.Request;
import net.grpc.Respond;
import net.grpc.ToyServiceGrpc;

import java.util.logging.Logger;

public class GrpcClient{
    private static final Logger logger = Logger.getLogger(GrpcClient.class.getName());
    static final String DEFAULT_HOST = "localhost";
    static final int DEFAULT_PORT = 7077;
    //args[0]: host args[1]: port
    public static void main(String[] args) throws InterruptedException {
        if(args.length == 0) {
            logger.info(runGRPC(new RequestGRPCEntity("Method", "Tux")));
        } else {
            logger.info(runGRPC(new RequestGRPCEntity("Method", "Tux"), args[0], Integer.parseInt(args[1])));
        }
    }

    public static String runGRPC(RequestGRPCEntity request, String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        ToyServiceGrpc.ToyServiceBlockingStub stub = ToyServiceGrpc.newBlockingStub(channel);

        Respond response;
        if(request.getMethod().equals("buy")) {
            response = stub.buy(Request.newBuilder()
                    .setToyName(request.getToyName())
                    .build());
        } else {
            response = stub.query(Request.newBuilder()
                    .setToyName(request.getToyName())
                    .build());
        }
        channel.shutdown();
        return request.getMethod().equals("buy")
                ? "successFlag: " + response.getSuccess() + "."
                : "price: " + response.getPrice()
                + ", stock: " + response.getStock()
                + ", successFlag: " + response.getSuccess() + ".";
    }

    public static String runGRPC(RequestGRPCEntity request) {
        return runGRPC(request, DEFAULT_HOST, DEFAULT_PORT);
    }


}
