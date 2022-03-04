//package net.service;
//
//import net.Products.Stock;
//import net.utils.Response;
//import io.grpc.stub.StreamObserver;
//import net.grpc.ToyServiceGrpc;
//import net.grpc.ToyServiceOuterClass;
//
//public class ToyServiceImpl extends ToyServiceGrpc.ToyServiceImplBase{
//
//    ToyServiceOuterClass.Response.Builder response = ToyServiceOuterClass.Response.newBuilder();
////    @Override
////    public void query(ToyServiceOuterClass.Request request, StreamObserver<ToyServiceOuterClass.Response> responseObserver) {
////        String toyName = request.getToyName();
////        Stock stock = Stock.getInstance();
////        Response res = stock.query(toyName);
////        response.setPrice((float) res.price).setSuccess(res.state);
////    }
//
//
//    @Override
//    public void query(ToyServiceOuterClass.Request request, StreamObserver<ToyServiceOuterClass.Response> responseObserver) {
//        super.query(request, responseObserver);
//    }
//
//    @Override
//    public void buy(ToyServiceOuterClass.Request request, StreamObserver<ToyServiceOuterClass.Response> responseObserver) {
//        String toyName = request.getToyName();
//        Stock stock = Stock.getInstance();
//        Response res = stock.buy(toyName);
//        response.setSuccess(res.state).setPrice((float) stock.getPrice(toyName));
//    }
//
//    public void handle(Stock stock){
////        if(method.equals("query")){
////            Response response = query(toyName);
////            if(response.state != 1) out.println("From Server: "+ response.state);
////            else out.println("From Server: "+ response.price);
////
////        }
////        else if(method.equals("buy")){
////            Response response = buy(toyName);
////            out.println("From Server: "+response.state);
////        }
//    }
//
//
//
//
//}
