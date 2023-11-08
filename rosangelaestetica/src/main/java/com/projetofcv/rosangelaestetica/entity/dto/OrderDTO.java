package com.projetofcv.rosangelaestetica.entity.dto;

import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projetofcv.rosangelaestetica.entity.Order;
import com.projetofcv.rosangelaestetica.entity.User;
import com.projetofcv.rosangelaestetica.entity.Work;
import com.projetofcv.rosangelaestetica.entity.enums.OrderStatus;

public class OrderDTO {

    private Long id;
    private String date;
    private String time;
    private OrderStatus orderStatus;
    private User userClient;
    private Work workOrder;

    @JsonIgnore
    DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @JsonIgnore
    DateTimeFormatter formatterT = DateTimeFormatter.ofPattern("HH:mm");

    public OrderDTO() {

    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.date = order.getDate().format(formatterD);
        this.time = order.getTime().format(formatterT);
        this.orderStatus = order.getOrderStatus();
        if (order.getUserClient() != null) {
            this.userClient = order.getUserClient();
        }
        if (order.getWorkOrder() != null) {
            this.workOrder = order.getWorkOrder();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUserClient() {
        return userClient;
    }

    public void setUserClient(User userClient) {
        this.userClient = userClient;
    }

    public Work getWorkOrder() {
        return workOrder;
    }

    public void setWorkOrder(Work workOrder) {
        this.workOrder = workOrder;
    }

    public DateTimeFormatter getFormatterD() {
        return formatterD;
    }

    public void setFormatterD(DateTimeFormatter formatterD) {
        this.formatterD = formatterD;
    }

    public DateTimeFormatter getFormatterT() {
        return formatterT;
    }

    public void setFormatterT(DateTimeFormatter formatterT) {
        this.formatterT = formatterT;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        OrderDTO other = (OrderDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
