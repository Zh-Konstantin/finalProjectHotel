package com.example.model.entity;


/**
 * A model class for rooms database table
 */
public class Room implements Entity {

    private int apartmentNumber;
    private int sleepingPlaces;
    private RoomClass roomClass;
    private RoomStatus status;
    private double price;
    private String imgPath;

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public int getSleepingPlaces() {
        return sleepingPlaces;
    }

    public void setSleepingPlaces(int sleepingPlaces) {
        this.sleepingPlaces = sleepingPlaces;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public Room() {
    }

    public Room(int apartmentNumber, int sleepingPlaces, RoomClass roomClass, RoomStatus status, double price) {
        this.apartmentNumber = apartmentNumber;
        this.sleepingPlaces = sleepingPlaces;
        this.roomClass = roomClass;
        this.status = status;
        this.price = price;
        this.imgPath = imgPath;
    }
}
