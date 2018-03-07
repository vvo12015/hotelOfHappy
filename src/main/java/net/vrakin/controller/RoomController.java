package net.vrakin.controller;

import net.vrakin.model.Category;
import net.vrakin.model.Room;
import net.vrakin.model.Service;
import net.vrakin.repository.CategoryRepository;
import net.vrakin.repository.RoomRepository;
import net.vrakin.repository.ServiceRepository;
import net.vrakin.services.CategoryService;
import net.vrakin.services.RoomService;
import net.vrakin.services.ServiceService;
import net.vrakin.workClasses.Ajax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ServiceService serviceService;

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public Map<String, Object> getRooms(){
        Iterable<Room> rooms = roomService.findAll();

        return Ajax.successResponse(rooms);
    }
}
