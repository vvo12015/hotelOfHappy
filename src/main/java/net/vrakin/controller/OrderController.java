package net.vrakin.controller;

import net.vrakin.exceptions.RestException;
import net.vrakin.model.*;
import net.vrakin.services.*;
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

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public Map<String, Object> getAllBooking(){
        return Ajax.successResponse(new ArrayList<>(orderService.findAll()));
    }

    @RequestMapping(value="/create-order", method = RequestMethod.POST)
    public Map<String, Object> createOrder(@RequestBody OrderFromForm orderFromForm){
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

        order.setServiceList(orderFromForm.getServices()
                    .stream()
                    .map(sn-> serviceService.get(Long.valueOf(sn)))
                    .collect(Collectors.toList()));

        if (orderService.validationOrderDates(startDate,
                finishDate,order.getRoom())){
            orderService.save(order);

            return
                    Ajax.successResponse(orderService.findByUser(order.getUser()));
        }else {

            Map<String, Object> data = getFreeRoomsCategoriesList(order.getRoom().getCategory(), startDate, finishDate);

            return data;
        }

    }
    @RequestMapping(value = "/free-rooms-categories", method = RequestMethod.POST)
    public Map<String, Object> getFreeRoomsCategories
            (@RequestBody OrderFromForm orderFromForm){
        Date startDate = orderFromForm.getStartDate();
        Date finishDate = orderFromForm.getFinishDate();
        if (startDate == null || finishDate == null){
            startDate = new Date(new java.util.Date().getTime());
            finishDate = startDate;
        }
        return getFreeRoomsCategoriesList(startDate,
                finishDate);
    }

    @RequestMapping(value = "/totalSum", method = RequestMethod.POST)
    public Map<String, Object> getTotalSum
            (@RequestBody OrderFromForm orderFromForm){

        Float totalSum = 0.0F;
        List<String> serviceList = orderFromForm.getServices();
        for (String serviceId :
                serviceList) {
            totalSum += serviceService.get(Long.valueOf(serviceId)).getPrice();
        }
        String roomNumber = orderFromForm.getNumberRoom();

        totalSum += roomService.findByNumber(roomNumber).getPrice();


        return Ajax.successResponse(totalSum);
    }

    private Map<String, Object> getFreeRoomsCategoriesList(Date startDate, Date finishDate) {
        return getFreeRoomsCategoriesList(categoryService.findAll(), startDate, finishDate);
    }

    private Map<String, Object> getFreeRoomsCategoriesList(Category category, Date startDate, Date finishDate) {
        List<Category> categories = Collections.singletonList(category);

        return getFreeRoomsCategoriesList(categories, startDate, finishDate);
    }

    private Map<String, Object> getFreeRoomsCategoriesList(List<Category> categories, Date startDate, Date finishDate) {
        List<Room> roomsOfEnterCategory = roomService.findFreeRooms(startDate, finishDate,
                categories);

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

        data.put("freeRoomOfCategory",
                roomsOfEnterCategory.stream()
                .map(Room::getNumber)
                .collect(Collectors.toSet()));
        data.put("allFreeCategories", allFreeCategories.stream()
                .map(Category::getName)
                .collect(Collectors.toSet()));
        return Ajax.successResponse(data);
    }
}
