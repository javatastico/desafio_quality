package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.RoomDto;

import java.util.List;

public interface PropertyService {
    List<RoomDto> getAreaRoom(Long id);
    List<PropertyDto> getPropertyArea(Long idProperty);

}
