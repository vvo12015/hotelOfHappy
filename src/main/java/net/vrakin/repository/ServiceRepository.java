package net.vrakin.repository;

import net.vrakin.model.Category;
import net.vrakin.model.Order;
import net.vrakin.model.Room;
import net.vrakin.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long>{

}
