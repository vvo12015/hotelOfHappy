package net.vrakin.model;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrderFromForm {

    private String numberRoom;
    private Date startDate;
    private Date finishDate;
    private String firstName;
    private String lastName;
    private List<String> services;
    private String totalPrice;
    private String categoryName;

    public OrderFromForm() {
    }

    public OrderFromForm(Order order){
        this.firstName = order.getUser().getFirstName();
        this.lastName = order.getUser().getLastName();
        this.startDate = order.getStartDate();
        this.finishDate = order.getFinishDate();
        this.categoryName = order.getRoom().getCategory().getName();
        this.services = order.getServiceList()
                .stream()
                .map(Service::getName)
                .collect(Collectors.toList());
        this.numberRoom = order.getRoom().getNumber();
        this.totalPrice = "$" + order.getPrice().toString();
    }

    public String getNumberRoom() {
        return numberRoom;
    }

    public void setNumberRoom(String numberRoom) {
        this.numberRoom = numberRoom;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getServices() {
        return services;
    }

    public void setServices(List<String> services) {
        this.services = services;
    }
}
