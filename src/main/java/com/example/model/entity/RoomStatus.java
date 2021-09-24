package com.example.model.entity;

public enum RoomStatus {
    FREE("free"),
    PENDING_PAYMENT("pending payment"),
    BOOKED("booked"),
    NOT_AVAILABLE("not available");

    private String name;

    RoomStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
