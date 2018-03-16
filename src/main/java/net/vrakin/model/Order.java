package net.vrakin.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "USER_REF")
    private User user;

    @ManyToOne
    @JoinColumn(name = "ROOM_REF")
    private Room room;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "FINISH_DATE")
    private Date finishDate;

    @ManyToMany
    @JoinTable(name = "ORDER_SERVICE",
            joinColumns = @JoinColumn(name = "ORDER_REF"),
            inverseJoinColumns = @JoinColumn(name = "SERVICE_REF"))
    private List<Service> serviceList;

    public Order(User user, Room room, Date startDate, Date finishDate, List<Service> serviceList) {
        this.user = user;
        this.room = room;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.serviceList = serviceList;
    }

    public Order() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    public List<Service> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<Service> serviceList) {
        this.serviceList = serviceList;
    }

    public Float getPrice(){
        Float totalSum = this.getRoom().getPrice();
        for (Service service :
                this.serviceList) {
            totalSum += service.getPrice();
        }
        return totalSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(user, order.user) &&
                Objects.equals(room, order.room) &&
                Objects.equals(startDate, order.startDate) &&
                Objects.equals(finishDate, order.finishDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, room, startDate, finishDate);
    }

    @Override
    public String toString() {
        return "Order{" +
                "user=" + user +
                ", room=" + room +
                ", startDate=" + startDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
