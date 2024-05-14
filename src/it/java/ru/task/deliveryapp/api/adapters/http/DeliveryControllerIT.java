package ru.task.deliveryapp.api.adapters.http;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import ru.task.deliveryapp.api.adapters.http.contract.model.CourierModel;
import ru.task.deliveryapp.api.adapters.http.contract.model.OrderModel;
import ru.task.deliveryapp.core.application.usecases.commands.CreateOrderCommand;
import ru.task.deliveryapp.core.application.usecases.commands.StartWorkCommand;
import ru.task.deliveryapp.core.application.usecases.commands.StopWorkCommand;
import ru.task.deliveryapp.core.domain.sharedkernel.Weight;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.task.deliveryapp.api.adapters.http.DeliveryApi.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DeliveryControllerIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testCreateOrder() {
        var response = restTemplate.postForEntity(CREATE_ORDER, new CreateOrderCommand(UUID.randomUUID(), "Location", Weight.create(5)), CreateOrderCommand.class);
        assertAll(String.format("Testing %s endpoint", CREATE_ORDER),
                () -> assertEquals(HttpStatus.CREATED, response.getStatusCode())
        );
    }

    @Test
    public void testCreateOrder_negative_exists() {
        var response = restTemplate.postForEntity(CREATE_ORDER, new CreateOrderCommand(UUID.fromString("977a4941-2baa-4a36-9a7f-6ce587064c71"), "Location", Weight.create(5)), CreateOrderCommand.class);
        assertAll(String.format("Testing %s endpoint, the order is already exists", CREATE_ORDER),
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testStartWork() {
        var response = restTemplate.postForEntity("/api/v1/couriers/bf79a004-56d7-4e5f-a21c-0a9e5e08d10d/start-work", new StartWorkCommand(UUID.randomUUID()), StartWorkCommand.class);
        assertAll(String.format("Testing %s endpoint", START_WORK),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testStartWork_negative_wrong_status() {
        var response = restTemplate.postForEntity("/api/v1/couriers/407f68be-5adf-4e72-81bc-b1d8e9574cf8/start-work", new StartWorkCommand(UUID.randomUUID()), StartWorkCommand.class);
        assertAll(String.format("Testing %s endpoint", START_WORK),
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testStartWork_negative_not_found() {
        var response = restTemplate.postForEntity("/api/v1/couriers/21282439-0381-446c-8df7-6543cba02c55/start-work", new StartWorkCommand(UUID.randomUUID()), StartWorkCommand.class);
        assertAll(String.format("Testing %s endpoint when courier not found", START_WORK),
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testStopWork() {
        var response = restTemplate.postForEntity("/api/v1/couriers/324d8231-32bf-4159-b360-4705aa80ce1f/stop-work", new StopWorkCommand(UUID.randomUUID()), StopWorkCommand.class);
        assertAll(String.format("Testing %s endpoint", START_WORK),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testStopWork_negative_wrong_status() {
        var response = restTemplate.postForEntity("/api/v1/couriers/407f68be-5adf-4e72-81bc-b1d8e9574cf8/stop-work", new StartWorkCommand(UUID.randomUUID()), StartWorkCommand.class);
        assertAll(String.format("Testing %s endpoint when courier is busy", STOP_WORK),
                () -> assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testStopWork_negative_not_found() {
        var response = restTemplate.postForEntity("/api/v1/couriers/21282439-0381-446c-8df7-6543cba02c55/stop-work", new StartWorkCommand(UUID.randomUUID()), StartWorkCommand.class);
        assertAll(String.format("Testing %s endpoint when courier not found", STOP_WORK),
                () -> assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode())
        );
    }

    @Test
    @Sql("/courier_data.sql")
    public void testGetAllCouriers() {
        var response = restTemplate.getForEntity(GET_ALL_COURIERS, CourierModel[].class);
        CourierModel couriers[] = response.getBody();
        var courierList = Arrays.stream(couriers).sorted((c1, c2)->c1.id().toString().compareTo(c2.id().toString())).toList();
        assertAll(String.format("Testing %s endpoint", GET_ALL_COURIERS),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(4, couriers.length),
                () -> assertEquals(UUID.fromString("324d8231-32bf-4159-b360-4705aa80ce1f"), courierList.get(0).id()),
                () -> assertEquals("Courier 2", courierList.get(0).name())
        );
    }

    @Test
    @Sql("/order_data.sql")
    public void testGetActiveOrders() {
        var response = restTemplate.getForEntity(GET_ACTIVE_ORDERS, OrderModel[].class);
        OrderModel orders[] = response.getBody();
        var orderList = Arrays.stream(orders).sorted((o1, o2)->o1.id().toString().compareTo(o2.id().toString())).toList();
        assertAll(String.format("Testing %s endpoint", GET_ACTIVE_ORDERS),
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () -> assertEquals(3, orders.length),
                () ->assertEquals(UUID.fromString("2a053bdb-c027-42ab-a0e4-0f2b67e68c7b"), orderList.get(0).id())
        );
    }
}
