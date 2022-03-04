package net.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.15.0)",
    comments = "Source: ToyService.proto")
public final class ToyServiceGrpc {

  private ToyServiceGrpc() {}

  public static final String SERVICE_NAME = "ToyService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.grpc.Request,
      net.grpc.Respond> getQueryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "query",
      requestType = net.grpc.Request.class,
      responseType = net.grpc.Respond.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.grpc.Request,
      net.grpc.Respond> getQueryMethod() {
    io.grpc.MethodDescriptor<net.grpc.Request, net.grpc.Respond> getQueryMethod;
    if ((getQueryMethod = ToyServiceGrpc.getQueryMethod) == null) {
      synchronized (ToyServiceGrpc.class) {
        if ((getQueryMethod = ToyServiceGrpc.getQueryMethod) == null) {
          ToyServiceGrpc.getQueryMethod = getQueryMethod = 
              io.grpc.MethodDescriptor.<net.grpc.Request, net.grpc.Respond>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ToyService", "query"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.grpc.Respond.getDefaultInstance()))
                  .setSchemaDescriptor(new ToyServiceMethodDescriptorSupplier("query"))
                  .build();
          }
        }
     }
     return getQueryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.grpc.Request,
      net.grpc.Respond> getBuyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "buy",
      requestType = net.grpc.Request.class,
      responseType = net.grpc.Respond.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.grpc.Request,
      net.grpc.Respond> getBuyMethod() {
    io.grpc.MethodDescriptor<net.grpc.Request, net.grpc.Respond> getBuyMethod;
    if ((getBuyMethod = ToyServiceGrpc.getBuyMethod) == null) {
      synchronized (ToyServiceGrpc.class) {
        if ((getBuyMethod = ToyServiceGrpc.getBuyMethod) == null) {
          ToyServiceGrpc.getBuyMethod = getBuyMethod = 
              io.grpc.MethodDescriptor.<net.grpc.Request, net.grpc.Respond>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "ToyService", "buy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.grpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  net.grpc.Respond.getDefaultInstance()))
                  .setSchemaDescriptor(new ToyServiceMethodDescriptorSupplier("buy"))
                  .build();
          }
        }
     }
     return getBuyMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ToyServiceStub newStub(io.grpc.Channel channel) {
    return new ToyServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ToyServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ToyServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ToyServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ToyServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ToyServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void query(net.grpc.Request request,
        io.grpc.stub.StreamObserver<net.grpc.Respond> responseObserver) {
      asyncUnimplementedUnaryCall(getQueryMethod(), responseObserver);
    }

    /**
     */
    public void buy(net.grpc.Request request,
        io.grpc.stub.StreamObserver<net.grpc.Respond> responseObserver) {
      asyncUnimplementedUnaryCall(getBuyMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getQueryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                net.grpc.Request,
                net.grpc.Respond>(
                  this, METHODID_QUERY)))
          .addMethod(
            getBuyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                net.grpc.Request,
                net.grpc.Respond>(
                  this, METHODID_BUY)))
          .build();
    }
  }

  /**
   */
  public static final class ToyServiceStub extends io.grpc.stub.AbstractStub<ToyServiceStub> {
    private ToyServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ToyServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ToyServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ToyServiceStub(channel, callOptions);
    }

    /**
     */
    public void query(net.grpc.Request request,
        io.grpc.stub.StreamObserver<net.grpc.Respond> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getQueryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void buy(net.grpc.Request request,
        io.grpc.stub.StreamObserver<net.grpc.Respond> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBuyMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ToyServiceBlockingStub extends io.grpc.stub.AbstractStub<ToyServiceBlockingStub> {
    private ToyServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ToyServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ToyServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ToyServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.grpc.Respond query(net.grpc.Request request) {
      return blockingUnaryCall(
          getChannel(), getQueryMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.grpc.Respond buy(net.grpc.Request request) {
      return blockingUnaryCall(
          getChannel(), getBuyMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ToyServiceFutureStub extends io.grpc.stub.AbstractStub<ToyServiceFutureStub> {
    private ToyServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ToyServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ToyServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ToyServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.grpc.Respond> query(
        net.grpc.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getQueryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.grpc.Respond> buy(
        net.grpc.Request request) {
      return futureUnaryCall(
          getChannel().newCall(getBuyMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_QUERY = 0;
  private static final int METHODID_BUY = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ToyServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ToyServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_QUERY:
          serviceImpl.query((net.grpc.Request) request,
              (io.grpc.stub.StreamObserver<net.grpc.Respond>) responseObserver);
          break;
        case METHODID_BUY:
          serviceImpl.buy((net.grpc.Request) request,
              (io.grpc.stub.StreamObserver<net.grpc.Respond>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ToyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ToyServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return net.grpc.ToyServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ToyService");
    }
  }

  private static final class ToyServiceFileDescriptorSupplier
      extends ToyServiceBaseDescriptorSupplier {
    ToyServiceFileDescriptorSupplier() {}
  }

  private static final class ToyServiceMethodDescriptorSupplier
      extends ToyServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ToyServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ToyServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ToyServiceFileDescriptorSupplier())
              .addMethod(getQueryMethod())
              .addMethod(getBuyMethod())
              .build();
        }
      }
    }
    return result;
  }
}
