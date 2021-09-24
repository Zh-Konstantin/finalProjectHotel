package com.example.model.entity;

/**
 * A model class for orders database table
 */
public class Order implements Entity {

    private int id;
    private Room room;
    private User user;
    private int daysNumber;
    private int peoplesCount;
    private double totalSum;
    private OrderStatus status = OrderStatus.NEW;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDaysNumber() {
        return daysNumber;
    }

    public void setDaysNumber(int daysNumber) {
        this.daysNumber = daysNumber;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public int getPeoplesCount() {
        return peoplesCount;
    }

    public void setPeoplesCount(int peoplesCount) {
        this.peoplesCount = peoplesCount;
    }

    public Order() {
    }

    public Order(int id, Room room, User user, int daysNumber, int peoplesCount, double totalSum, OrderStatus status) {
        this.id = id;
        this.room = room;
        this.user = user;
        this.daysNumber = daysNumber;
        this.peoplesCount = peoplesCount;
        this.totalSum = totalSum;
        this.status = status;
    }
}
