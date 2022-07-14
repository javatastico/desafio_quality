package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;

import java.util.List;

public interface PropertyRepository {

    public Property save(Property property);
    public List<Room> getRoom(Long idProperty);
    public Property getProperty(Long idProperty);

}
