package net.vrakin.services;

import net.vrakin.repository.ServiceRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceService implements ParentService<net.vrakin.model.Service, Long> {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public Long getId(net.vrakin.model.Service entity) {
        return entity.getServiceId();
    }

    @Override
    public CrudRepository<net.vrakin.model.Service, Long> getRepository() {
        return this.serviceRepository;
    }
    
}
