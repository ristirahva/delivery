package ru.task.deliveryapp.infrastructure.adapters.grpc.fwoservice;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import org.springframework.stereotype.Service;
import ru.task.deliveryapp.Contract;
import ru.task.deliveryapp.core.domain.sharedkernel.Location;

@Service
public class GeoClient {
    public Location getLocation(String address) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 5004)
                .usePlaintext()
                .build();
        GeoGrpc.GeoBlockingStub stub
                = GeoGrpc.newBlockingStub(channel);

        Contract.GetGeolocationReply geoResponse = stub.getGeolocation(Contract.GetGeolocationRequest.newBuilder()
                .setAddress(address)
                .build());

        channel.shutdown();
        return Location.create(geoResponse.getLocation().getX(), geoResponse.getLocation().getY());
    }
}
