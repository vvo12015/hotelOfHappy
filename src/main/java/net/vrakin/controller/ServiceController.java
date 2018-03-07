package net.vrakin.controller;

import net.vrakin.model.Service;
import net.vrakin.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    List<Service> getAllService(){
        return serviceRepository.findAll();
    }
}
