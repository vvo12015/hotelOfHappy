package net.vrakin.controller;

import net.vrakin.exceptions.RestException;
import net.vrakin.model.*;
import net.vrakin.services.CategoryService;
import net.vrakin.services.OrderService;
import net.vrakin.services.RoomService;
import net.vrakin.services.UserService;
import net.vrakin.workClasses.Ajax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public List<Order> getAllBooking(){
        return new ArrayList<>(orderService.findAll());
    }

    @RequestMapping(value="/create-order", method = RequestMethod.POST)
    public Map<String, Object> createOrder(@RequestBody OrderFromForm orderFromForm)
        throws RestException{
        Order order = new Order();

        String roomNumber = orderFromForm.getNumberRoom();

        if (roomService.findByNumber(roomNumber) != null)
            order.setRoom(roomService.findByNumber(roomNumber));
        else
            return Ajax.errorResponse("Wrong number room");

        User user = userService.findByFirstNameAndLastName(orderFromForm.getFirstName(),
                orderFromForm.getLastName());
        if ( user != null){
            order.setUser(user);
        }else{
            user = new User();
            user.setFirstName(orderFromForm.getFirstName());
            user.setLastName(orderFromForm.getLastName());

            userService.save(user);

            order.setUser(userService.findByFirstNameAndLastName(orderFromForm.getFirstName(),
                    orderFromForm.getLastName()));
        }

        Date startDate = orderFromForm.getStartDate();
        Date finishDate = orderFromForm.getFinishDate();
        order.setStartDate(startDate);
        order.setFinishDate(finishDate);

        if (orderService.validationOrderDates(startDate,
                finishDate,order.getRoom())){
            orderService.save(order);

            return
                    Ajax.successResponse(orderService.findByUser(order.getUser()));
        }else {
            List<Room> roomsOfEnterCategory = roomService.findFreeRooms(startDate, finishDate,
                    new ArrayList<>(Collections.singletonList(order.getRoom().getCategory())));


            List<Room> allFreeRooms =
                            Collections.checkedList(roomService
                                    .findFreeRooms(
                                            startDate,
                                            finishDate,
                                            categoryService.findAll()),
                            Room.class);

            List<Category> allFreeCategories = allFreeRooms
                    .stream()
                    .map(Room::getCategory)
                    .collect(Collectors.toList());

            Map<String, Object> data = new HashMap<>();

            data.put("freeRoomOfCategory", roomsOfEnterCategory);
            data.put("allFreeCategories", allFreeCategories);

            return Ajax.successResponse(data);
        }

    }
}
