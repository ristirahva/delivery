package ru.task.deliveryapp.infrastructure.adapters.grpc.fwoservice;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The Geo service definition.
 * </pre>
 */
@javax.annotation.Generated(
        value = "by gRPC proto compiler (version 1.64.0)",
        comments = "Source: Contract.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GeoGrpc {

    private GeoGrpc() {}

    public static final java.lang.String SERVICE_NAME = "Geo";

    // Static method descriptors that strictly reflect the proto.
    private static volatile io.grpc.MethodDescriptor<ru.task.deliveryapp.Contract.GetGeolocationRequest,
            ru.task.deliveryapp.Contract.GetGeolocationReply> getGetGeolocationMethod;

    @io.grpc.stub.annotations.RpcMethod(
            fullMethodName = SERVICE_NAME + '/' + "GetGeolocation",
            requestType = ru.task.deliveryapp.Contract.GetGeolocationRequest.class,
            responseType = ru.task.deliveryapp.Contract.GetGeolocationReply.class,
            methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
    public static io.grpc.MethodDescriptor<ru.task.deliveryapp.Contract.GetGeolocationRequest,
            ru.task.deliveryapp.Contract.GetGeolocationReply> getGetGeolocationMethod() {
        io.grpc.MethodDescriptor<ru.task.deliveryapp.Contract.GetGeolocationRequest, ru.task.deliveryapp.Contract.GetGeolocationReply> getGetGeolocationMethod;
        if ((getGetGeolocationMethod = GeoGrpc.getGetGeolocationMethod) == null) {
            synchronized (GeoGrpc.class) {
                if ((getGetGeolocationMethod = GeoGrpc.getGetGeolocationMethod) == null) {
                    GeoGrpc.getGetGeolocationMethod = getGetGeolocationMethod =
                            io.grpc.MethodDescriptor.<ru.task.deliveryapp.Contract.GetGeolocationRequest, ru.task.deliveryapp.Contract.GetGeolocationReply>newBuilder()
                                    .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
                                    .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetGeolocation"))
                                    .setSampledToLocalTracing(true)
                                    .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            ru.task.deliveryapp.Contract.GetGeolocationRequest.getDefaultInstance()))
                                    .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                                            ru.task.deliveryapp.Contract.GetGeolocationReply.getDefaultInstance()))
                                    .setSchemaDescriptor(new GeoMethodDescriptorSupplier("GetGeolocation"))
                                    .build();
                }
            }
        }
        return getGetGeolocationMethod;
    }

    /**
     * Creates a new async stub that supports all call types for the service
     */
    public static GeoStub newStub(io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<GeoStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<GeoStub>() {
                    @java.lang.Override
                    public GeoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new GeoStub(channel, callOptions);
                    }
                };
        return GeoStub.newStub(factory, channel);
    }

    /**
     * Creates a new blocking-style stub that supports unary and streaming output calls on the service
     */
    public static GeoBlockingStub newBlockingStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<GeoBlockingStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<GeoBlockingStub>() {
                    @java.lang.Override
                    public GeoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new GeoBlockingStub(channel, callOptions);
                    }
                };
        return GeoBlockingStub.newStub(factory, channel);
    }

    /**
     * Creates a new ListenableFuture-style stub that supports unary calls on the service
     */
    public static GeoFutureStub newFutureStub(
            io.grpc.Channel channel) {
        io.grpc.stub.AbstractStub.StubFactory<GeoFutureStub> factory =
                new io.grpc.stub.AbstractStub.StubFactory<GeoFutureStub>() {
                    @java.lang.Override
                    public GeoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
                        return new GeoFutureStub(channel, callOptions);
                    }
                };
        return GeoFutureStub.newStub(factory, channel);
    }

    /**
     * <pre>
     * The Geo service definition.
     * </pre>
     */
    public interface AsyncService {

        /**
         * <pre>
         * Get Geolocation
         * </pre>
         */
        default void getGeolocation(ru.task.deliveryapp.Contract.GetGeolocationRequest request,
                                    io.grpc.stub.StreamObserver<ru.task.deliveryapp.Contract.GetGeolocationReply> responseObserver) {
            io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetGeolocationMethod(), responseObserver);
        }
    }

    /**
     * Base class for the server implementation of the service Geo.
     * <pre>
     * The Geo service definition.
     * </pre>
     */
    public static abstract class GeoImplBase
            implements io.grpc.BindableService, AsyncService {

        @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
            return GeoGrpc.bindService(this);
        }
    }

    /**
     * A stub to allow clients to do asynchronous rpc calls to service Geo.
     * <pre>
     * The Geo service definition.
     * </pre>
     */
    public static final class GeoStub
            extends io.grpc.stub.AbstractAsyncStub<GeoStub> {
        private GeoStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected GeoStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new GeoStub(channel, callOptions);
        }

        /**
         * <pre>
         * Get Geolocation
         * </pre>
         */
        public void getGeolocation(ru.task.deliveryapp.Contract.GetGeolocationRequest request,
                                   io.grpc.stub.StreamObserver<ru.task.deliveryapp.Contract.GetGeolocationReply> responseObserver) {
            io.grpc.stub.ClientCalls.asyncUnaryCall(
                    getChannel().newCall(getGetGeolocationMethod(), getCallOptions()), request, responseObserver);
        }
    }

    /**
     * A stub to allow clients to do synchronous rpc calls to service Geo.
     * <pre>
     * The Geo service definition.
     * </pre>
     */
    public static final class GeoBlockingStub
            extends io.grpc.stub.AbstractBlockingStub<GeoBlockingStub> {
        private GeoBlockingStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected GeoBlockingStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new GeoBlockingStub(channel, callOptions);
        }

        /**
         * <pre>
         * Get Geolocation
         * </pre>
         */
        public ru.task.deliveryapp.Contract.GetGeolocationReply getGeolocation(ru.task.deliveryapp.Contract.GetGeolocationRequest request) {
            return io.grpc.stub.ClientCalls.blockingUnaryCall(
                    getChannel(), getGetGeolocationMethod(), getCallOptions(), request);
        }
    }

    /**
     * A stub to allow clients to do ListenableFuture-style rpc calls to service Geo.
     * <pre>
     * The Geo service definition.
     * </pre>
     */
    public static final class GeoFutureStub
            extends io.grpc.stub.AbstractFutureStub<GeoFutureStub> {
        private GeoFutureStub(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            super(channel, callOptions);
        }

        @java.lang.Override
        protected GeoFutureStub build(
                io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
            return new GeoFutureStub(channel, callOptions);
        }

        /**
         * <pre>
         * Get Geolocation
         * </pre>
         */
        public com.google.common.util.concurrent.ListenableFuture<ru.task.deliveryapp.Contract.GetGeolocationReply> getGeolocation(
                ru.task.deliveryapp.Contract.GetGeolocationRequest request) {
            return io.grpc.stub.ClientCalls.futureUnaryCall(
                    getChannel().newCall(getGetGeolocationMethod(), getCallOptions()), request);
        }
    }

    private static final int METHODID_GET_GEOLOCATION = 0;

    private static final class MethodHandlers<Req, Resp> implements
            io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
            io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final AsyncService serviceImpl;
        private final int methodId;

        MethodHandlers(AsyncService serviceImpl, int methodId) {
            this.serviceImpl = serviceImpl;
            this.methodId = methodId;
        }

        @java.lang.Override
        @java.lang.SuppressWarnings("unchecked")
        public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
            switch (methodId) {
                case METHODID_GET_GEOLOCATION:
                    serviceImpl.getGeolocation((ru.task.deliveryapp.Contract.GetGeolocationRequest) request,
                            (io.grpc.stub.StreamObserver<ru.task.deliveryapp.Contract.GetGeolocationReply>) responseObserver);
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

    public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
        return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
                .addMethod(
                        getGetGeolocationMethod(),
                        io.grpc.stub.ServerCalls.asyncUnaryCall(
                                new MethodHandlers<
                                        ru.task.deliveryapp.Contract.GetGeolocationRequest,
                                        ru.task.deliveryapp.Contract.GetGeolocationReply>(
                                        service, METHODID_GET_GEOLOCATION)))
                .build();
    }

    private static abstract class GeoBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
        GeoBaseDescriptorSupplier() {}

        @java.lang.Override
        public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
            return ru.task.deliveryapp.Contract.getDescriptor();
        }

        @java.lang.Override
        public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
            return getFileDescriptor().findServiceByName("Geo");
        }
    }

    private static final class GeoFileDescriptorSupplier
            extends GeoBaseDescriptorSupplier {
        GeoFileDescriptorSupplier() {}
    }

    private static final class GeoMethodDescriptorSupplier
            extends GeoBaseDescriptorSupplier
            implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
        private final java.lang.String methodName;

        GeoMethodDescriptorSupplier(java.lang.String methodName) {
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
            synchronized (GeoGrpc.class) {
                result = serviceDescriptor;
                if (result == null) {
                    serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
                            .setSchemaDescriptor(new GeoFileDescriptorSupplier())
                            .addMethod(getGetGeolocationMethod())
                            .build();
                }
            }
        }
        return result;
    }
}

