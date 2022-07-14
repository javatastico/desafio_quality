package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.RoomDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PropertyService {
    List<RoomDto> getAreaRoom(Long id);
    RoomDto getBiggestRoom(Long id);
    PropertyDto getPropertyPrice(Long id);
    List<PropertyDto> getPropertyArea(Long idProperty);

}
