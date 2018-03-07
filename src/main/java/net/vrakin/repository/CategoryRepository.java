package net.vrakin.repository;

import net.vrakin.model.Category;
import net.vrakin.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityManager;

public interface CategoryRepository extends JpaRepository<Category, Long>{



}
