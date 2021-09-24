package com.example.model.entity;

public enum UserRole {
    CLIENT("client"),
    MANAGER("manager");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
