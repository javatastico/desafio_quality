package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.model.RoomDto;
import com.meli.desafio_quality.repository.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class PropertyServiceImp implements PropertyService {

    @Autowired
    private PropertyRepository repositoryProperty;

    @Override
    public List<RoomDto> getAreaRoom(Long id) {
        List<Room> listRoom = repositoryProperty.getRoom(id);
        List<RoomDto> listRoomDto = new ArrayList<>();

        listRoom.forEach( r -> {
            listRoomDto.add(RoomDto.builder()
                    .name(r.getName())
                    .area((r.getRoomLength() * r.getRoomWidth()))
                    .build());
        });
        return listRoomDto;
    }

    @Override
    public List<PropertyDto> getPropertyArea(Long idProperty) {
        List<Room> listRoom = repositoryProperty.getRoom(idProperty);

        List<PropertyDto> propertyDtoList = new ArrayList<>();

        Double totalPropertySquare = listRoom.stream()
                .mapToDouble(area -> area.getRoomLength() * area.getRoomWidth()).sum();
        log.info("Total square: " + totalPropertySquare);

        listRoom.forEach(room -> {
            propertyDtoList.add(PropertyDto.builder()
                            .name(room.getName())
                    .build());
        });





        return null;
    }
}
