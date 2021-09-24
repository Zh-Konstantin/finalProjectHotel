package com.example.model.entity;

public enum RoomClass implements Entity {
    ECONOMY("economy"),
    STANDARD("standard"),
    LUX("lux");

    private String name;

    RoomClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
