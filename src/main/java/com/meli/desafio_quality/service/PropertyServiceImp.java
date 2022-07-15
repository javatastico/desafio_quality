package com.meli.desafio_quality.service;

import com.meli.desafio_quality.dto.PropertyRequestSave;
import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.dto.RoomDto;
import com.meli.desafio_quality.model.*;
import com.meli.desafio_quality.repository.PropertyRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
                        BigDecimal.ZERO,
                        (partialPriceResult, room) -> partialPriceResult.add(propertyDto.getDistrict().getValueM2()
                            .multiply(BigDecimal.valueOf(room.getArea()))),
                        BigDecimal::add)
        );

        return propertyDto;
    }

    @Override
    public PropertyResponseTotalSquare getPropertyArea(Long idProperty) {
        Property property = repositoryProperty.getProperty(idProperty);
        PropertyResponseTotalSquare propertyArea = new PropertyResponseTotalSquare();

        Double totalPropertySquare = property.getListRoom().stream()
                .mapToDouble(area -> area.getRoomLength() * area.getRoomWidth()).sum();
        log.info("Total square: " + totalPropertySquare);

        propertyArea.setTotalPropertySquare(totalPropertySquare);
        propertyArea.setName(property.getName());

        return propertyArea;
    }

    @Override
    public Property save(PropertyRequestSave propertyRequestSave) {
        return repositoryProperty.save(Property.builder()
                .name(propertyRequestSave.getName())
                .district(propertyRequestSave.getDistrict())
                .listRoom(propertyRequestSave.getListRoom())
                .build());
    }

    @Override
    public List<Property> getAllProperties() {
        return repositoryProperty.getAllProperties();
    }
}
