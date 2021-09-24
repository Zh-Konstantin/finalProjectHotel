package com.example.model.dao;

import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO implementation for the MySQL relational database
 */
public class MysqlRoomDao implements RoomDao {

    private final static Logger logger = LogManager.getLogger(MysqlUserDao.class.getSimpleName());
    private Connection connection;

    public MysqlRoomDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean delete(Integer id) throws UnsuccessfulRequestException {
        //not needed now
        return false;
    }

    @Override
    public boolean create(Room entity) throws UnsuccessfulRequestException {
        //not needed now
        return false;
    }

    @Override
    public boolean refreshStatus(int apartmentNumber, String status) throws UnsuccessfulRequestException {
        int changes;
        try(PreparedStatement statement = connection.prepareStatement(
                "UPDATE rooms " +
                        "SET status = ? " +
                        "WHERE apartment_number = ?;")){
            statement.setString(1, status);
            statement.setInt(2, apartmentNumber);
            changes = statement.executeUpdate();
        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        }
        return changes > 0;
    }

    @Override
    public Room findRoomByNumber(int apartmentNumber) throws UnsuccessfulRequestException {
        Room room;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT apartment_number, sleeping_places, room_class, status, price, img_path " +
                        "FROM rooms " +
                        "WHERE apartment_number = ?;")){
            statement.setInt(1, apartmentNumber);
            resultSet = statement.executeQuery();
            room = parseSingleEntity(resultSet);

        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return room;
    }

    /**
     * Helper-method which encapsulates getting a single entity
     * from the ResultSet object
     * @param rs ResultSet object, which represents the result of an SQL-query
     * @return model entity
     * @throws SQLException in case when there is an SQL-related error
     */
    private Room parseSingleEntity(ResultSet rs) throws SQLException {
        Room room = null;
        if (rs.next()){
            room = new Room();
            room.setApartmentNumber(rs.getInt("apartment_number"));
            room.setSleepingPlaces(rs.getInt("sleeping_places"));

            String roomClass = rs.getString("room_class");
            if (roomClass.equalsIgnoreCase(RoomClass.ECONOMY.getName()))
                room.setRoomClass(RoomClass.ECONOMY);
            else if (roomClass.equalsIgnoreCase(RoomClass.STANDARD.getName()))
                room.setRoomClass(RoomClass.STANDARD);
            else if (roomClass.equalsIgnoreCase(RoomClass.LUX.getName()))
                room.setRoomClass(RoomClass.LUX);

            String status = rs.getString("status");
            if (status.equalsIgnoreCase(RoomStatus.FREE.getName()))
                room.setStatus(RoomStatus.FREE);
            else if (status.equalsIgnoreCase(RoomStatus.BOOKED.getName()))
                room.setStatus(RoomStatus.BOOKED);
            else if (status.equalsIgnoreCase(RoomStatus.PENDING_PAYMENT.getName()))
                room.setStatus(RoomStatus.PENDING_PAYMENT);
            else if (status.equalsIgnoreCase(RoomStatus.NOT_AVAILABLE.getName()))
                room.setStatus(RoomStatus.NOT_AVAILABLE);

            room.setPrice(rs.getDouble("price"));
            room.setImgPath(rs.getString("img_path"));
        }
        return room;
    }

    @Override
    public List<Room> getFreeRooms() throws UnsuccessfulRequestException {
        List<Room> rooms;
        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM rooms " +
                        "WHERE status = ? ;")){
            statement.setString(1, "free");
            resultSet = statement.executeQuery();
            rooms = parseToEntityList(resultSet);

        } catch (SQLException e){
            logger.debug(e.getMessage());
            throw new UnsuccessfulRequestException(e.getMessage(), e.getCause());
        } finally {
            closeResultSet(resultSet);
        }
        return rooms;
    }

    /**
     * Helper-method which encapsulates getting a list of
     * entities from a ResultSet object
     * @param resultSet ResultSet object, which represents the result of an SQL-query
     * @return list of model entities
     * @throws SQLException in case when there is an SQL-related error
     */
    private List<Room> parseToEntityList(ResultSet resultSet) throws SQLException {
        List<Room> items = new ArrayList<>();
        Room room;
        while(resultSet.next()){
            room = new Room();
            room.setApartmentNumber(resultSet.getInt("apartment_number"));
            room.setSleepingPlaces(resultSet.getInt("sleeping_places"));

            String roomClass = resultSet.getString("room_class");
            if (roomClass.equalsIgnoreCase(RoomClass.LUX.getName()))
                room.setRoomClass(RoomClass.LUX);
            else if (roomClass.equalsIgnoreCase(RoomClass.STANDARD.getName()))
                room.setRoomClass(RoomClass.STANDARD);
            else if (roomClass.equalsIgnoreCase(RoomClass.ECONOMY.getName()))
                room.setRoomClass(RoomClass.ECONOMY);

            room.setPrice(resultSet.getDouble("price"));

            String status = resultSet.getString("status");
            if (status.equals(RoomStatus.FREE.getName()))
                room.setStatus(RoomStatus.FREE);
            else if (status.equals(RoomStatus.PENDING_PAYMENT.getName()))
                room.setStatus(RoomStatus.PENDING_PAYMENT);
            else if (status.equals(RoomStatus.BOOKED.getName()))
                room.setStatus(RoomStatus.BOOKED);

            room.setImgPath(resultSet.getString("img_path"));

            items.add(room);
        }
        return items;
    }

    private void closeResultSet(ResultSet resultSet){
        try {
            if(resultSet!=null)
                resultSet.close();
        } catch (SQLException e) {
            logger.debug(e.getMessage());
        }
    }
}
