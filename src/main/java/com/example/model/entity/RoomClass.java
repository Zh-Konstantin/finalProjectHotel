package com.example.model.entity;

public enum RoomClass implements Entity {
    ECONOMY("economy", 1),
    STANDARD("standard", 2),
    LUX("lux", 3);

    private String name;
    private int point;

    RoomClass(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public int getPoint() {
        return point;
    }
}
