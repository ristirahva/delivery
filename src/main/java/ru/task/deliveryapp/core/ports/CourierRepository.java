package ru.task.deliveryapp.core.ports;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.task.deliveryapp.core.domain.courieraggregate.Courier;
import org.springframework.data.repository.CrudRepository;
import ru.task.deliveryapp.core.domain.courieraggregate.CourierStatus;

import java.util.Collection;

@Repository
public interface CourierRepository extends CrudRepository<Courier, Long> {
    @Query(value = "SELECT * FROM courier c WHERE c.status=1", nativeQuery = true)
    Collection<Courier> getAllActive();
}
