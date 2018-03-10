package net.vrakin.repository;

import net.vrakin.model.Category;
import net.vrakin.model.Order;
import net.vrakin.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findByCategory(Category category);

    @Query("select r from Room r " +
            "where (r.category in :categories) and " +
            "(r not in (" +
            "select o.room from Order o " +
            "where (o.startDate <= :startDate and " +
            "o.finishDate >= :startDate) or " +
            "(o.startDate >= :startDate and " +
            "o.startDate <= :finishDate)))")
    List<Room> findFreeRooms(@Param("startDate") Date startDate,
                               @Param("finishDate") Date finishDate,
                               @Param("categories") List<Category> categories);

    Room findByNumber(String numberRoom);
}
