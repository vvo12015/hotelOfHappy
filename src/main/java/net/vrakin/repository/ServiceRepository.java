package net.vrakin.repository;

import net.vrakin.model.Category;
import net.vrakin.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long>{

}
