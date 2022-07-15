package com.meli.desafio_quality.service;

import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.RoomDto;
import com.meli.desafio_quality.repository.PropertyRepository;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyServiceImpTest {

    @InjectMocks
    private PropertyServiceImp service;

    @Mock
    private PropertyRepository repository;

    @BeforeEach
    public void setup(){
        BDDMockito.when(repository.getRoom(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getRoom());
        BDDMockito.when(repository.getProperty(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getPropertyWithId());
    }

    @Test
    void getAreaRoom_returnListRoomDto_whenPropertyExists() {
        Property property = TestUtilsGenerator.getPropertyWithId();

        List<RoomDto> returnExpected = TestUtilsGenerator.getRoomDto();

        List<RoomDto> returnedRoomDto = service.getAreaRoom(property.getId());

        assertThat(returnedRoomDto.get(0).getArea()).isPositive();
        assertThat(returnedRoomDto.get(0).getArea()).isEqualTo(returnExpected.get(0).getArea());
        assertThat(returnedRoomDto.get(0).getName()).isEqualTo(returnExpected.get(0).getName());

        verify(repository, atLeastOnce()).getRoom(property.getId());

    }

    @Test
    void getBiggestRoom_returnTheBiggestRoomDtoOnAProperty() {
        Property property = TestUtilsGenerator.getPropertyWithId();

        RoomDto returnBiggetRoomDtoExpected = TestUtilsGenerator.getBiggestRoomDto();

        RoomDto returnedRoomDto = service.getBiggestRoom(property.getId());

        assertThat(returnedRoomDto.getArea()).isPositive();
        assertThat(returnedRoomDto.getArea()).isEqualTo(returnBiggetRoomDtoExpected.getArea());
        assertThat(returnedRoomDto.getName()).isEqualTo(returnBiggetRoomDtoExpected.getName());

        verify(repository, atLeastOnce()).getRoom(property.getId());
    }

    @Test
    void getPropertyPriceTest(){
        Property property = TestUtilsGenerator.getPropertyWithId();

        PropertyResponseTotalPrice returnPropertyDto = TestUtilsGenerator.getPropertyDto();
        PropertyResponseTotalPrice propertyDto = service.getPropertyPrice(property.getId());

        assertThat(propertyDto.getPrice()).isEqualTo(returnPropertyDto.getPrice());
        assertThat(propertyDto.getName()).isEqualTo(returnPropertyDto.getName());
        assertThat(propertyDto.getRooms().get(0).getArea()).isEqualTo(
                returnPropertyDto.getRooms().get(0).getArea());

        verify(repository, atLeastOnce()).getProperty(property.getId());
    }


    @Test
    void getPropertyArea_returnPropertyResponseTotalSquare_whenPropertyIdExist() {

        PropertyResponseTotalSquare expectedTotalSquare = TestUtilsGenerator.getResponseTotalSquare();
        Property property = TestUtilsGenerator.getPropertyWithId();

        List<PropertyResponseTotalSquare> responseTotalSquare = service.getPropertyArea(property.getId());

        assertThat(responseTotalSquare.get(0).getName()).isEqualTo(expectedTotalSquare.getName());
        assertThat(responseTotalSquare.get(0).getTotalPropertySquare()).isEqualTo(
                expectedTotalSquare.getTotalPropertySquare()
        );
        assertThat(responseTotalSquare.get(0).getTotalPropertySquare()).isPositive();

    }
}