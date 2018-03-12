package net.vrakin.repository;

import net.vrakin.model.Category;
import net.vrakin.model.Order;
import net.vrakin.model.Room;
import net.vrakin.model.Service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ServiceRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testFindByRoom(){
        Room room = roomRepository.findOne(1L);
        Category category = room.getCategory();

        assertThat(category.getServiceList().size() == 2);
    }
}
