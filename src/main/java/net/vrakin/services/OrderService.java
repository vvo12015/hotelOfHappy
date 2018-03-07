package net.vrakin.services;

import net.vrakin.model.Order;
import net.vrakin.repository.OrderRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class OrderService implements ParentService<Order, Long> {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Long getId(Order entity) {
        return entity.getOrderId();
    }

    @Override
    public CrudRepository<Order, Long> getRepository() {
        return this.orderRepository;
    }

    public List<Order> findBusyOrders(Date startDate, Date finishDate){
        List<Order> orderList = orderRepository.findBusyOrders(startDate, finishDate);

        return orderList;
    }
    
}
