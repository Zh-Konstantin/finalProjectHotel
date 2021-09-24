package com.example.service;

import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.DBManager;
import com.example.model.dao.RoomDao;
import com.example.model.dao.factory.DAOFactory;
import com.example.model.dao.factory.MySqlDAOFactory;
import com.example.model.entity.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Business logic layer representative, used for communicating with database
 * abstracting from concrete database realization
 */
public class RoomService {

    private final static Logger LOGGER = LogManager.getLogger(RoomService.class.getSimpleName());
    private DAOFactory factory;
    private DBManager manager;

    /**
     * Creates RoomService entity with default (MySql) factory
     */
    public RoomService() {
        this.factory = new MySqlDAOFactory();
        manager = DBManager.getInstance();
    }

    public List<Room> getFreeRooms() throws UnsuccessfulRequestException {
        RoomDao roomDao = factory.createRoomDao(manager.getConnection());
        return roomDao.getFreeRooms();
    }

    public Room findRoomByNumber(int apartmentNumber) throws UnsuccessfulRequestException {
        RoomDao roomDao = factory.createRoomDao(manager.getConnection());
        return roomDao.findRoomByNumber(apartmentNumber);
    }

    public boolean refreshStatus(int apartmentNumber, String status) throws UnsuccessfulRequestException {
        RoomDao roomDao = factory.createRoomDao(manager.getConnection());
        return roomDao.refreshStatus(apartmentNumber, status);
    }

}
