package net.vrakin.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "ORDERS")
@NamedQuery(name = "Order.findBusyOrders",
query = "select o from Order o " +
        "where (o.startDate <= :finishDate and " +
        "o.startDate >= :startDate) or " +
        "(o.finishDate <= :finishDate and " +
        "o.finishDate >= :startDate) or " +
        "(o.startDate <= :startDate and o.finishDate >= :finishDate)")
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

    public Order(User user, Room room, Date startDate, Date finishDate) {
        this.user = user;
        this.room = room;
        this.startDate = startDate;
        this.finishDate = finishDate;
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
