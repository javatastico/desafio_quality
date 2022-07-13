package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.model.RoomDto;
import com.meli.desafio_quality.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
}
