package net.vrakin.controller;

import net.vrakin.exceptions.RestException;
import net.vrakin.model.Order;
import net.vrakin.model.OrderFromForm;
import net.vrakin.model.Room;
import net.vrakin.services.OrderService;
import net.vrakin.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getAllBooking(){
        List<Order> orders = new ArrayList((Collection) orderService.findAll());
        return orders;
    }

    @RequestMapping(value="/create-order", method = RequestMethod.POST)
    public String createOrder(OrderFromForm orderFromForm)
        throws RestException{
        Order order = new Order();

        String roomNumber = orderFromForm.getNumberRoom();
        Iterable<Room> rooms = roomService.findAll();

        for (Room room :
                rooms) {
            if (room.getNumber().equals(roomNumber)){
                order.setRoom(room);
                break;
            }
        }

        Date startDate = orderFromForm.getStartDate();
        Date finishDate = orderFromForm.getFinishDate();

        List<Order> orders = orderService.findBusyOrders(startDate, finishDate);

        return "/";
    }
}
