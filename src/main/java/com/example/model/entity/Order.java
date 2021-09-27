package com.example.model.entity;

/**
 * A model class for orders database table
 */
public class Order implements Entity {

    private int id;
    private int roomId;
    private int userId;
    private int daysNumber;
    private int peoplesCount;
    private double totalSum;
    private OrderStatus status = OrderStatus.NEW;
    private RoomClass roomClass;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public Order() {
    }

    public Order(int id, int room, int user, int daysNumber, int peoplesCount, double totalSum, OrderStatus status, RoomClass roomClass) {
        this.id = id;
        this.roomId = room;
        this.userId = user;
        this.daysNumber = daysNumber;
        this.peoplesCount = peoplesCount;
        this.totalSum = totalSum;
        this.status = status;
        this.roomClass = roomClass;
    }

    public Order(int userId, int daysNumber, int peoplesCount, RoomClass roomClass) {
        this.userId = userId;
        this.daysNumber = daysNumber;
        this.peoplesCount = peoplesCount;
        this.roomClass = roomClass;
    }

    public String getStatusRus() {
        switch (status) {
            case NEW: return "ожидает обработки";
            case IN_CONFIRM: return "ожидает подтверждения";
            case PENDING_PAYMENT: return "ожидает оплаты";
            case PAID: return "оплачен";
            case CANCELED: return "отменен";
        }
        return "неизвестный";
    }

    public boolean inConfirmation(){
        return status == OrderStatus.IN_CONFIRM;
    }

    public boolean isNew(){
        return status == OrderStatus.NEW;
    }
}
