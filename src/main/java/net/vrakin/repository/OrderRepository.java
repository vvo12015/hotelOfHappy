package net.vrakin.repository;

import net.vrakin.model.Order;
import net.vrakin.model.Room;
import net.vrakin.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("select o from Order o " +
                    "where (o.startDate <= :finishDate and " +
                    "o.startDate >= :startDate) or " +
                    "(o.finishDate <= :finishDate and " +
                    "o.finishDate >= :startDate) or " +
                    "(o.startDate <= :startDate and o.finishDate >= :finishDate) " +
            "and o.room = :room")
    List<Order> findBusyOrders(@Param("startDate") Date startDate,
                               @Param("finishDate") Date finishDate,
                               @Param("room") Room room);

    List<Order> findByUser(User user);

}
