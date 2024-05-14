package ru.task.deliveryapp.api.adapters.http.contract.model;

import ru.task.deliveryapp.core.domain.aggregate.order.Order;

import java.util.ArrayList;
import java.util.List;

public class GetActiveOrdersMapper {
    public static List<OrderModel> toModel(List<Order> orderList) {
        var orderModelList = new ArrayList<OrderModel>();
        for (Order order : orderList) {
            var orderModel = new OrderModel(order.getId(), order.getLocation());
            orderModelList.add(orderModel);
        }
        return orderModelList;
    }
}
