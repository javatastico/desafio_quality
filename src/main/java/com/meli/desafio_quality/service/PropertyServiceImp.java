package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.PropertyDto;
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

    @Override
    public RoomDto getBiggestRoom(Long id) {
        List<RoomDto> listRoom = getAreaRoom(id);
        return listRoom.stream().max((rOne, rTwo) -> rOne.getArea().compareTo(rTwo.getArea())).get();
    }

    @Override
    public PropertyDto getPropertyPrice(Long id) {
        Property property = repositoryProperty.getProperty(id);
        PropertyDto propertyDto = new PropertyDto();
        List<RoomDto> listRoomDto = new ArrayList<>();

        property.getListRoom().forEach( r -> {
            listRoomDto.add(RoomDto.builder()
                    .name(r.getName())
                    .area((r.getRoomLength() * r.getRoomWidth()))
                    .build());
        });
        propertyDto.setName(property.getName());
        propertyDto.setDistrict(property.getDistrict());
        propertyDto.setRooms(listRoomDto);
        propertyDto.setPrice(propertyDto
                .getRooms()
                .stream()
                .reduce(
                0D, (partialPriceResult, room) -> partialPriceResult + room.getArea(), Double::sum)
                * propertyDto.getDistrict().getValueM2());

        return propertyDto;
    }
}
