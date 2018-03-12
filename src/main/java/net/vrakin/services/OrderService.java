package net.vrakin.services;

import net.vrakin.model.Order;
import net.vrakin.model.Room;
import net.vrakin.model.User;
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

    public Boolean validationOrderDates(Date startDate, Date finishDate, Room room){
        List<Order> busyOrders = orderRepository.findBusyOrders(startDate,
                finishDate, room);
        return busyOrders.size() == 0;
    }

    public List<Order> findByUser(User user){
        return orderRepository.findByUser(user);
    }

    public Float getTotalPrice(Order order){
        final Float[] sumServices = {0f};
        order.getServiceList().stream().forEach(s-> sumServices[0] += s.getPrice());
        return order.getPrice() + sumServices[0];
    }
}
