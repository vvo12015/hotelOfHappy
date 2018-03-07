package net.vrakin.controller;

import net.vrakin.model.Category;
import net.vrakin.model.Room;
import net.vrakin.repository.CategoryRepository;
import net.vrakin.services.CategoryService;
import net.vrakin.services.RoomService;
import net.vrakin.workClasses.Ajax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RoomService roomService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public Iterable<Category> getAllCategories(){
        return categoryService.findAll();
    }

    @RequestMapping(value = "/room-of-category/{categoryId}", method = RequestMethod.GET)
    public Map<String, Object> getRoomsOfCategory(
            @PathVariable("categoryId") Long categotyId){
        Category category = categoryService.get(categotyId);

        List<Room> rooms = roomService.findByCategory(category);
        return Ajax.successResponse(rooms);
    }
}
