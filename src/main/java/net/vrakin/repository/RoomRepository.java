package net.vrakin.repository;

import net.vrakin.model.Category;
import net.vrakin.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.ListIterator;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findByCategory(Category category);
}
