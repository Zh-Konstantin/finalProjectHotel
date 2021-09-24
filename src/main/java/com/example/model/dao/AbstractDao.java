package com.example.model.dao;


import com.example.exceptions.UnsuccessfulRequestException;
import com.example.model.entity.Entity;

import java.util.List;

/**
 * Basic DAO class which is extended by concrete DAO classes
 * @param <K> represents key of the database table
 * @param <T> represents entity type
 */
public interface AbstractDao<K, T extends Entity> {
    boolean delete(K id) throws UnsuccessfulRequestException;
    boolean create(T entity) throws UnsuccessfulRequestException;
}
