package com.example.model.entity;


/**
 * A model class for invoice database table
 */
public class Invoice implements Entity {

    private int id;
    private int roomId;
    private int userId;
    private int orderId;
    private double sum;
    private boolean paid = false;
    private long date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public Invoice() {
    }

    public Invoice(int id, int roomId, int userId, int orderId, double sum, boolean paid, long date) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.orderId = orderId;
        this.sum = sum;
        this.paid = paid;
        this.date = date;
    }

    public Invoice(int roomId, int userId, int orderId, double sum, boolean paid, long date) {
        this.roomId = roomId;
        this.userId = userId;
        this.orderId = orderId;
        this.sum = sum;
        this.paid = paid;
        this.date = date;
    }
}
