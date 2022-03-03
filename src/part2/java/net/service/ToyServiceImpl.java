package net.service;

import io.grpc.stub.StreamObserver;
import net.grpc.ToyServiceGrpc;
import net.grpc.ToyServiceOuterClass;

public class ToyServiceImpl extends ToyServiceGrpc.ToyServiceImplBase implements Runnable{
    public ToyServiceImpl(){

    }
    @Override
    public void query(ToyServiceOuterClass.Request request, StreamObserver<ToyServiceOuterClass.Response> responseObserver) {
        String toyName = request.getToyName();
    }

    @Override
    public void buy(ToyServiceOuterClass.Request request, StreamObserver<ToyServiceOuterClass.Response> responseObserver) {
        String toyName = request.getToyName();

    }

    @Override
    public void run() {

    }
}
