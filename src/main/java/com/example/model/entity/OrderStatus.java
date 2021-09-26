package com.example.model.entity;

public enum OrderStatus {
    NEW ("new"),
    IN_CONFIRM ("inConfirm"),
    PENDING_PAYMENT("pending payment"),
    CANCELED ("canceled"),
    PAID ("paid");

    private String name;

    public String getName() {
        return name;
    }

    OrderStatus(String name) {
        this.name = name;
    }
}
