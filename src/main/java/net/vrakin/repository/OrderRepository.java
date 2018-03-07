package net.vrakin.repository;

import net.vrakin.model.Order;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    List<Order> findBusyOrders(Date startDate, Date finishDate);

}
