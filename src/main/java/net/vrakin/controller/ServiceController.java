package net.vrakin.controller;

import net.vrakin.model.Category;
import net.vrakin.model.OrderFromForm;
import net.vrakin.model.Room;
import net.vrakin.model.Service;
import net.vrakin.repository.ServiceRepository;
import net.vrakin.services.RoomService;
import net.vrakin.workClasses.Ajax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    List<Service> getAllService(){
        return serviceRepository.findAll();
    }

    @RequestMapping(value = "/serviceOfRoom", method = RequestMethod.POST)
    public Map<String, Object> getServicesOfRoom(
            @RequestBody OrderFromForm orderFromForm){

        Room room = roomService.findByNumber(orderFromForm.getNumberRoom());
        Category category = room.getCategory();
        Map<String, Object> data = new HashMap<>();
        data.put("services", category.getServiceList());
        data.put("priceRoom", room.getPrice());

        return Ajax.successResponse(data);
    }
}
