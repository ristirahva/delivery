package ru.task.deliveryapp.api.adapters.http.contract.model;

import ru.task.deliveryapp.core.domain.aggregate.courier.Courier;

import java.util.ArrayList;
import java.util.List;

public class GetCouriersMapper {
    public static List<CourierModel> toModel(List<Courier> courierList) {
        var courierModelList = new ArrayList<CourierModel>();
        for (Courier courier : courierList) {
            var courierModel = new CourierModel(courier.getId(), courier.getName(), courier.getLocation());
            courierModelList.add(courierModel);
        }
        return courierModelList;
    }
}
