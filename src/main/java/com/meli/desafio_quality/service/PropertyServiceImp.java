package com.meli.desafio_quality.service;

import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.dto.RoomDto;
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

        listRoom.forEach(r -> {
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
    public PropertyResponseTotalPrice getPropertyPrice(Long id) {
        Property property = repositoryProperty.getProperty(id);
        PropertyResponseTotalPrice propertyDto = new PropertyResponseTotalPrice();
        List<RoomDto> listRoomDto = new ArrayList<>();

        property.getListRoom().forEach(r -> {
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

    @Override
    public List<PropertyResponseTotalSquare> getPropertyArea(Long idProperty) {
        Property property = repositoryProperty.getProperty(idProperty);
        List<PropertyResponseTotalSquare> propertyArea = new ArrayList<>();

        Double totalPropertySquare = property.getListRoom().stream()
                .mapToDouble(area -> area.getRoomLength() * area.getRoomWidth()).sum();
        log.info("Total square: " + totalPropertySquare);

        propertyArea.add(PropertyResponseTotalSquare.builder()
                        .name(property.getName())
                .totalPropertySquare(totalPropertySquare).build());

        return propertyArea;
    }
}
