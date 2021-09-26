package com.example.model.entity;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A model class for invoice database table
 */
public class Invoice implements Entity {

    private int id;
    private int roomId;
    private int userId;
    private int orderId;
    private double sum;
    private long date;
    private InvoiceStatus status = InvoiceStatus.PENDING_PAYMENT;

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

    public InvoiceStatus getStatus() {
        return status;
    }
    public void setStatus(InvoiceStatus status) {
        this.status = status;
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

    public Invoice(int id, int roomId, int userId, int orderId, double sum, long date, InvoiceStatus status) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.orderId = orderId;
        this.sum = sum;
        this.status = status;
        this.date = date;
    }

    public Invoice(int roomId, int userId, int orderId, double sum, long date) {
        this.roomId = roomId;
        this.userId = userId;
        this.orderId = orderId;
        this.sum = sum;
        this.date = date;
    }

    public boolean isPendingPayment(){
        return status == InvoiceStatus.PENDING_PAYMENT;
    }


    public String getStatusRus(){
        if (status == InvoiceStatus.PAID)
            return "оплачен";
        if (status == InvoiceStatus.REJECTED)
            return "отменен";
        return "ожидает оплаты";
    }

    public String getDateAsStr() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(this.date);
        return formatter.format(date);
    }
}
