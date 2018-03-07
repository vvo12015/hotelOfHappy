package net.vrakin.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="ROOM")
public class Room {

    @Id
    @Column(name = "ROOM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long roomId;

    @Column(name = "NUMBER")
    @Size(min = 3, message = "Enter at least 3 characters")
    private String number;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_REF")
    private Category category;

    @Column(name = "PRICE")
    private Float price;

    @ManyToMany
    @JoinTable(name = "ROOM_SERVICE",
    joinColumns = @JoinColumn(name = "ROOM_REF"),
    inverseJoinColumns = @JoinColumn(name = "SERVICE_REF"))
    private List<Service> serviceList;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Room(String number, Category category, Float price, List<Service> serviceList) {
        this.number = number;
        this.category = category;
        this.price = price;
        this.serviceList = serviceList;
    }

    public Room() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(number, room.number) &&
                Objects.equals(category, room.category) &&
                Objects.equals(price, room.price);
    }

    @Override
    public int hashCode() {

        return Objects.hash(number, category, price);
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }
}
