package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.PropertyRequestSave;
import com.meli.desafio_quality.model.RoomDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PropertyService {
    List<RoomDto> getAreaRoom(Long id);
    RoomDto getBiggestRoom(Long id);
    PropertyDto getPropertyPrice(Long id);
    Property save(PropertyRequestSave propertyRequestSave);
    List<Property> getAllProperties();
}
