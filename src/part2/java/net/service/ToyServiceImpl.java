package net.service;

import io.grpc.stub.StreamObserver;
import net.Products.Stock;
import net.grpc.Request;
import net.grpc.Respond;
import net.grpc.ToyServiceGrpc;
import net.utils.Response;

public class ToyServiceImpl extends ToyServiceGrpc.ToyServiceImplBase{




    @Override
    public void query(Request request, StreamObserver<Respond> responseObserver) {
        String toyName = request.getToyName();
        Stock stock = Stock.getInstance();
        Response res = stock.query(toyName);
        responseObserver.onNext(Respond
                .newBuilder()
                .setPrice((float) res.price)
                .setSuccess(res.state)
                .build());
        responseObserver.onCompleted();
    }

    @Override
    public void buy(Request request, StreamObserver<Respond> responseObserver) {
        String toyName = request.getToyName();
        Stock stock = Stock.getInstance();
        Response res = stock.buy(toyName);
        responseObserver.onNext(Respond
                .newBuilder()
                .setPrice((float) res.price)
                .setSuccess(res.state)
                .build());
        responseObserver.onCompleted();

    }

    //    @Override
//    public void buy(ToyServiceOuterClass.Request request, StreamObserver<ToyServiceOuterClass.Response> responseObserver) {
//        String toyName = request.getToyName();
//        Stock stock = Stock.getInstance();
//        Response res = stock.buy(toyName);
//        response.setSuccess(res.state).setPrice((float) stock.getPrice(toyName));
//    }




}
