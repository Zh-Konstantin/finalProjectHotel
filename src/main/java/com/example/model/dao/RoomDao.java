package com.example.model.dao;


import com.example.model.entity.Order;
import com.example.model.entity.Room;

import java.util.List;

/**
 * Basic interface used for implementing DAOFactory for switching between databases easily
 */
public interface RoomDao  extends AbstractDao<Integer, Room>  {
    List<Room> getFreeRooms();
    Room findRoomByNumber(int apartmentNumber);
    boolean refreshStatus(int apartmentNumber, String status);
}
