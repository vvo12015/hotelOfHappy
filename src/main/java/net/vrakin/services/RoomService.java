package net.vrakin.services;

import net.vrakin.model.Category;
import net.vrakin.model.Room;
import net.vrakin.repository.RoomRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ListIterator;

@Service
public class RoomService implements ParentService<Room, Long> {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Long getId(Room entity) {
        return entity.getRoomId();
    }

    @Override
    public CrudRepository<Room, Long> getRepository() {
        return this.roomRepository;
    }

    public List<Room> findByCategory(Category category){
        return this.roomRepository.findByCategory(category);
    }
}
