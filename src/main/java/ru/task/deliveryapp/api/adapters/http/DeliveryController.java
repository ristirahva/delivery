package ru.task.deliveryapp.api.adapters.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.task.deliveryapp.api.adapters.http.contract.model.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import jakarta.annotation.Generated;
import ru.task.deliveryapp.core.application.usecases.commands.*;
import ru.task.deliveryapp.core.application.usecases.queries.GetActiveOrders;
import ru.task.deliveryapp.core.application.usecases.queries.GetAllCouriers;
import ru.task.deliveryapp.exception.DbException;
import ru.task.deliveryapp.exception.WrongStateException;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-05-02T22:44:02.959289500+03:00[Europe/Moscow]", comments = "Generator version: 7.5.0")
@RestController
@RequestMapping("${openapi.swaggerDelivery.base-path:}")
public class DeliveryController implements DeliveryApi {

    private static final Logger log = LoggerFactory.getLogger(DeliveryController.class);

    @Autowired
    private CreateOrderHandler createOrderHandler;

    @Autowired
    private StartWorkHandler startWorkHandler;

    @Autowired
    private StopWorkHandler stopWorkHandler;

    @Autowired
    private GetAllCouriers getAllCouriers;

    @Autowired
    private GetActiveOrders getActiveOrders;

    private final NativeWebRequest request;

    @Autowired
    public DeliveryController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createOrder(@RequestBody CreateOrderCommand command) {
        var status = HttpStatus.CREATED;
        try{
            createOrderHandler.handle(command);
        } catch (DbException dbe) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(status);
    }

    @Override
    public ResponseEntity<Void> startWork(@PathVariable UUID id) {
        var status = HttpStatus.OK;
        try {
            startWorkHandler.handle(new StartWorkCommand(id));
        } catch (WrongStateException wse) {
            status = HttpStatus.BAD_REQUEST;
        } catch (NoSuchElementException nsee) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);
    }

    @Override
    public ResponseEntity<Void> stopWork(@PathVariable UUID id) {
        var status = HttpStatus.OK;
        try {
            stopWorkHandler.handle(new StopWorkCommand(id));
        } catch (WrongStateException wse) {
            status = HttpStatus.BAD_REQUEST;
        } catch (NoSuchElementException nsee) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(status);
    }

    @Override
    public ResponseEntity<List<CourierModel>> getAllCouriers() {
        return new ResponseEntity<>(GetCouriersMapper.toModel(getAllCouriers.handle()), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderModel>> getActiveOrders() {
        return new ResponseEntity<>(GetActiveOrdersMapper.toModel(getActiveOrders.handle()), HttpStatus.OK);
    }

}
