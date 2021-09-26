package com.example.model.entity;

public enum InvoiceStatus {
    PENDING_PAYMENT("pending payment"),
    REJECTED("rejected"),
    PAID("paid");

    private String name;

    InvoiceStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
