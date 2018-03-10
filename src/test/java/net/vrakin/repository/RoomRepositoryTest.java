package net.vrakin.repository;

import net.vrakin.model.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testFindFreeRoomsAllFreeBefore(){
        Date startDate = Date.valueOf("2018-09-20");
        Date finishDate = Date.valueOf("2018-09-25");

        List<Room> roomList = roomRepository.findFreeRooms(startDate,
                finishDate, new ArrayList<>(Collections.singletonList(categoryRepository.findOne(3L))));

        assertThat(roomList.size() == 8);
    }

    @Test
    public void testFindFreeRoomsFirstBusyStartDate(){
        Date startDate = Date.valueOf("2018-09-20");
        Date finishDate = Date.valueOf("2018-10-23");

        List<Room> roomList = roomRepository.findFreeRooms(startDate,
                finishDate, new ArrayList<>(Collections.singletonList(categoryRepository.findOne(3L))));

        assertThat(roomList.size() == 7);
    }

    @Test
    public void testFindFreeRoomsFirstBusyFinishDate(){
        Date startDate = Date.valueOf("2018-09-20");
        Date finishDate = Date.valueOf("2018-10-25");

        List<Room> roomList = roomRepository.findFreeRooms(startDate,
                finishDate, new ArrayList<>(Collections.singletonList(categoryRepository.findOne(3L))));

        assertThat(roomList.size() == 7);
    }

    @Test
    public void testFindFreeRoomsAllFreeBetween(){
        Date startDate = Date.valueOf("2018-10-28");
        Date finishDate = Date.valueOf("2018-10-29");

        List<Room> roomList = roomRepository.findFreeRooms(startDate,
                finishDate, new ArrayList<>(Collections.singletonList(categoryRepository.findOne(3L))));

        assertThat(roomList.size() == 8);
    }

    @Test
    public void testFindFreeRoomsAllFreeAfter(){
        Date startDate = Date.valueOf("2018-11-06");
        Date finishDate = Date.valueOf("2018-11-11");

        List<Room> roomList = roomRepository.findFreeRooms(startDate,
                finishDate, new ArrayList<>(Collections.singletonList(categoryRepository.findOne(3L))));

        assertThat(roomList.size() == 8);
    }

    @Test
    public void testFindFreeRoomsLastBusyBetweenDate(){
        Date startDate = Date.valueOf("2018-11-05");
        Date finishDate = Date.valueOf("2018-11-11");

        List<Room> roomList = roomRepository.findFreeRooms(startDate,
                finishDate, new ArrayList<>(Collections.singletonList(categoryRepository.findOne(3L))));

        assertThat(roomList.size() == 7);
    }

}
