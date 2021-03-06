package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.PropertyRequestSave;
import com.meli.desafio_quality.dto.RoomDto;
import com.meli.desafio_quality.service.PropertyService;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PropertyControllerTest {

    @InjectMocks
    private PropertyController controller;

    @Mock
    PropertyService service;

    @BeforeEach
    public void setup() {
        BDDMockito.when(service.getAreaRoom(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getRoomDto());
        BDDMockito.when(service.getBiggestRoom(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getBiggestRoomDto());
        BDDMockito.when(service.getPropertyPrice(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getPropertyDto());
        BDDMockito.when(service.getAllProperties())
                .thenReturn(TestUtilsGenerator.getPropertyListWithId());
        BDDMockito.when(service.save(ArgumentMatchers.any(PropertyRequestSave.class)))
                .thenReturn(TestUtilsGenerator.getPropertyWithId());
        BDDMockito.when(service.getPropertyArea(ArgumentMatchers.anyLong()))
                .thenReturn(TestUtilsGenerator.getResponseTotalSquare());
    }

    @Test
    void getAreaRoom() {
        Property property = TestUtilsGenerator.getPropertyWithId();
        List<RoomDto> listRoomDtoExpected = TestUtilsGenerator.getRoomDto();

        ResponseEntity<List<RoomDto>> returnedList = controller.getAreaRoom(property.getId());

        assertThat(returnedList.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(returnedList.getBody().size()).isPositive();

        assertThat(returnedList.getBody()).isNotEmpty();

        assertThat(returnedList.getBody().get(0).getArea())
                .isEqualTo(listRoomDtoExpected.get(0).getArea());

        assertThat(returnedList.getBody().get(0).getName())
                .isEqualTo(listRoomDtoExpected.get(0).getName());

        Mockito.verify(service, Mockito.atLeastOnce()).getAreaRoom(property.getId());

    }

    @Test
    void getBiggestRoom() {
        Property property = TestUtilsGenerator.getPropertyWithId();
        RoomDto returnBiggetRoomDtoExpected = TestUtilsGenerator.getBiggestRoomDto();

        ResponseEntity<RoomDto> returnedRoomDto = controller.getBiggestRoom(property.getId());

        assertThat(returnedRoomDto.getStatusCode()).isEqualTo(HttpStatus.OK);


        assertThat(returnedRoomDto.getBody()).isNotNull();

        assertThat(returnedRoomDto.getBody().getArea())
                .isEqualTo(returnBiggetRoomDtoExpected.getArea());

        assertThat(returnedRoomDto.getBody().getName())
                .isEqualTo(returnBiggetRoomDtoExpected.getName());

        Mockito.verify(service, Mockito.atLeastOnce()).getBiggestRoom(property.getId());

    }

    @Test
    void getPropertyPrice() {
        Property property = TestUtilsGenerator.getPropertyWithId();
        PropertyResponseTotalPrice propertyDto =  TestUtilsGenerator.getPropertyDto();

        ResponseEntity<PropertyResponseTotalPrice> returnedPropertyDto = controller.getPropertyPrice(property.getId());

        assertThat(returnedPropertyDto.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(returnedPropertyDto.getBody()).isNotNull();

        assertThat(returnedPropertyDto.getBody().getName()).isEqualTo(propertyDto.getName());

        assertThat(returnedPropertyDto.getBody().getPrice()).isEqualTo(propertyDto.getPrice());

        assertThat(returnedPropertyDto.getBody().getDistrict()).isEqualTo(propertyDto.getDistrict());

        assertThat(returnedPropertyDto.getBody().getRooms()).isEqualTo(propertyDto.getRooms());

        Mockito.verify(service, Mockito.atLeastOnce()).getPropertyPrice(property.getId());
    }

    @Test
    void saveProperty() {
        PropertyRequestSave propertyRequestSave = TestUtilsGenerator.getNewPropertyRequestSave();

        ResponseEntity<Property> returnedProperty = controller.saveProperty(propertyRequestSave);

        assertThat(returnedProperty.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(returnedProperty.getBody()).isNotNull();
        assertThat(returnedProperty.getBody().getName()).isEqualTo(propertyRequestSave.getName());
        assertThat(returnedProperty.getBody().getDistrict()).isEqualTo(propertyRequestSave.getDistrict());
        assertThat(returnedProperty.getBody().getListRoom()).isEqualTo(propertyRequestSave.getListRoom());

        Mockito.verify(service, Mockito.atLeastOnce()).save(propertyRequestSave);
    }

    @Test
    void getAllProperties() {
        List<Property> propertyList = TestUtilsGenerator.getPropertyListWithId();

        ResponseEntity<List<Property>> returnedListProperty = controller.getAllProperties();

        assertThat(returnedListProperty.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(returnedListProperty.getBody()).isNotEmpty();
        assertThat(returnedListProperty.getBody()).isNotNull();
        assertThat(returnedListProperty.getBody().size()).isEqualTo(propertyList.size());
        assertThat(returnedListProperty.getBody().get(0)).isEqualTo(propertyList.get(0));
        assertThat(returnedListProperty.getBody().get(1)).isEqualTo(propertyList.get(1));

        Mockito.verify(service, Mockito.atLeastOnce()).getAllProperties();
    }

    @Test
    void getPropertyArea() {

        PropertyResponseTotalSquare expectedPropertyResponse = TestUtilsGenerator.getResponseTotalSquare();
        Property property = TestUtilsGenerator.getPropertyWithId();
        ResponseEntity<PropertyResponseTotalSquare> returnedPropertyArea = controller.getPropertyArea(property.getId());

        assertThat(returnedPropertyArea.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(returnedPropertyArea.getBody().getName())
                .isEqualTo(expectedPropertyResponse.getName());

        assertThat(returnedPropertyArea.getBody()).isNotNull();

        assertThat(returnedPropertyArea.getBody().getTotalPropertySquare())
                .isEqualTo(expectedPropertyResponse.getTotalPropertySquare());

        Mockito.verify(service, Mockito.atLeastOnce()).getPropertyArea(property.getId());

    }

}