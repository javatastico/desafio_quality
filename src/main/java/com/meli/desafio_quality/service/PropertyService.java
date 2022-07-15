package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.PropertyRequestSave;
import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.dto.RoomDto;

import java.util.List;

public interface PropertyService {
    List<RoomDto> getAreaRoom(Long id);
    RoomDto getBiggestRoom(Long id);
    PropertyResponseTotalPrice getPropertyPrice(Long id);
    List<PropertyResponseTotalSquare> getPropertyArea(Long idProperty);
    Property save(PropertyRequestSave propertyRequestSave);
    List<Property> getAllProperties();
}
