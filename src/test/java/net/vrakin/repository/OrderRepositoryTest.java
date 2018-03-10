package net.vrakin.repository;

import net.vrakin.model.Order;
import net.vrakin.model.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void testFindBusyOrdersBefore(){
        Date startDate = Date.valueOf("2018-09-20");
        Date finishDate = Date.valueOf("2018-09-25");

        Room room = roomRepository.findOne(1L);
        List<Order> orderList = orderRepository.findBusyOrders(startDate,
                finishDate, room);

        assertThat(orderList.size() == 0);
    }

    @Test
    public void testFindBusyOrdersFinishDateBetweenOrderDates(){
        Date startDate = Date.valueOf("2018-09-30");
        Date finishDate = Date.valueOf("2018-10-02");

        Room room = roomRepository.findOne(1L);
        List<Order> orderList = orderRepository.findBusyOrders(startDate,
                finishDate, room);

        Order expectedOrder = orderList.get(0);

        assertThat((orderList.size() == 1) && (expectedOrder.getOrderId().equals(1L)));
    }

    @Test
    public void testFindBusyOrdersStartDateBetweenOrderDates(){
        Date startDate = Date.valueOf("2018-10-02");
        Date finishDate = Date.valueOf("2018-10-08");

        Room room = roomRepository.findOne(1L);

        List<Order> orderList = orderRepository.findBusyOrders(startDate,
                finishDate, room);

        Order expectedOrder = orderList.get(0);
        assertThat((orderList.size() == 1) && (expectedOrder.getOrderId().equals(1L)));
    }

    @Test
    public void testFindBusyOrdersAllDateBetweenOrderDates(){
        Date startDate = Date.valueOf("2018-10-01");
        Date finishDate = Date.valueOf("2018-10-07");

        Room room = roomRepository.findOne(1L);

        List<Order> orderList = orderRepository.findBusyOrders(startDate,
                finishDate, room);

        Order expectedOrder = orderList.get(0);
        assertThat((orderList.size() == 1) && (expectedOrder.getOrderId().equals(1L)));
    }

    @Test
    public void testFindBusyOrdersAfter(){
        Date startDate = Date.valueOf("2018-10-08");
        Date finishDate = Date.valueOf("2018-10-09");

        Room room = roomRepository.findOne(1L);

        List<Order> orderList = orderRepository.findBusyOrders(startDate,
                finishDate, room);

        assertThat(orderList.size() == 0);
    }
}
