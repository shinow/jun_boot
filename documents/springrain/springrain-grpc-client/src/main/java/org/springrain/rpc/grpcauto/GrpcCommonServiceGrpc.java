package org.springrain.rpc.grpcauto;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * 定义通用的 Grpc 服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.35.0)",
    comments = "Source: grpcCommonService.proto")
public final class GrpcCommonServiceGrpc {

  private GrpcCommonServiceGrpc() {}

  public static final String SERVICE_NAME = "GrpcCommonService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<CommonRequest,
      CommonResponse> getCommonHandleMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "commonHandle",
      requestType = CommonRequest.class,
      responseType = CommonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<CommonRequest,
      CommonResponse> getCommonHandleMethod() {
    io.grpc.MethodDescriptor<CommonRequest, CommonResponse> getCommonHandleMethod;
    if ((getCommonHandleMethod = GrpcCommonServiceGrpc.getCommonHandleMethod) == null) {
      synchronized (GrpcCommonServiceGrpc.class) {
        if ((getCommonHandleMethod = GrpcCommonServiceGrpc.getCommonHandleMethod) == null) {
          GrpcCommonServiceGrpc.getCommonHandleMethod = getCommonHandleMethod =
              io.grpc.MethodDescriptor.<CommonRequest, CommonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "commonHandle"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CommonRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  CommonResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcCommonServiceMethodDescriptorSupplier("commonHandle"))
              .build();
        }
      }
    }
    return getCommonHandleMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcCommonServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcCommonServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcCommonServiceStub>() {
        @Override
        public GrpcCommonServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcCommonServiceStub(channel, callOptions);
        }
      };
    return GrpcCommonServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcCommonServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcCommonServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcCommonServiceBlockingStub>() {
        @Override
        public GrpcCommonServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcCommonServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcCommonServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcCommonServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcCommonServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcCommonServiceFutureStub>() {
        @Override
        public GrpcCommonServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcCommonServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcCommonServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * 定义通用的 Grpc 服务
   * </pre>
   */
  public static abstract class GrpcCommonServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public void commonHandle(CommonRequest request,
                             io.grpc.stub.StreamObserver<CommonResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCommonHandleMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCommonHandleMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                CommonRequest,
                CommonResponse>(
                  this, METHODID_COMMON_HANDLE)))
          .build();
    }
  }

  /**
   * <pre>
   * 定义通用的 Grpc 服务
   * </pre>
   */
  public static final class GrpcCommonServiceStub extends io.grpc.stub.AbstractAsyncStub<GrpcCommonServiceStub> {
    private GrpcCommonServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GrpcCommonServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcCommonServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public void commonHandle(CommonRequest request,
                             io.grpc.stub.StreamObserver<CommonResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCommonHandleMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   * 定义通用的 Grpc 服务
   * </pre>
   */
  public static final class GrpcCommonServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GrpcCommonServiceBlockingStub> {
    private GrpcCommonServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GrpcCommonServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcCommonServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public CommonResponse commonHandle(CommonRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCommonHandleMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 定义通用的 Grpc 服务
   * </pre>
   */
  public static final class GrpcCommonServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GrpcCommonServiceFutureStub> {
    private GrpcCommonServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected GrpcCommonServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcCommonServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 处理请求
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<CommonResponse> commonHandle(
        CommonRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCommonHandleMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COMMON_HANDLE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcCommonServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcCommonServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COMMON_HANDLE:
          serviceImpl.commonHandle((CommonRequest) request,
              (io.grpc.stub.StreamObserver<CommonResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcCommonServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcCommonServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return GrpcAutoCreateService.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcCommonService");
    }
  }

  private static final class GrpcCommonServiceFileDescriptorSupplier
      extends GrpcCommonServiceBaseDescriptorSupplier {
    GrpcCommonServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcCommonServiceMethodDescriptorSupplier
      extends GrpcCommonServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcCommonServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcCommonServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcCommonServiceFileDescriptorSupplier())
              .addMethod(getCommonHandleMethod())
              .build();
        }
      }
    }
    return result;
  }
}
