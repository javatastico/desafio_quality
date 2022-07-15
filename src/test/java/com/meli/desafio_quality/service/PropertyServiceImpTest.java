package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.PropertyRequestSave;
import com.meli.desafio_quality.model.RoomDto;
import com.meli.desafio_quality.repository.PropertyRepoImpl;
import com.meli.desafio_quality.repository.PropertyRepository;
import com.meli.desafio_quality.util.Data;
import com.meli.desafio_quality.util.DataLoaderTest;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
        BDDMockito.when(repository.save(ArgumentMatchers.any(Property.class)))
                .thenReturn(TestUtilsGenerator.getPropertyWithId());
        BDDMockito.when(repository.getAllProperties())
                .thenReturn(TestUtilsGenerator.getPropertyListWithId());
    }

    @Test
    void getAreaRoom_returnListRoomDto_whenPropertyExists() {
        Property property = TestUtilsGenerator.getPropertyWithId();

        List<RoomDto> returnExpected = TestUtilsGenerator.getRoomDto();

        List<RoomDto> returnedRoomDto = service.getAreaRoom(property.getId());

        assertThat(returnedRoomDto.get(0).getArea()).isPositive();
        assertThat(returnedRoomDto.get(0).getArea()).isEqualTo(returnExpected.get(0).getArea());
        assertThat(returnedRoomDto.get(0).getName()).isEqualTo(returnExpected.get(0).getName());

        Mockito.verify(repository,Mockito.atLeastOnce()).getRoom(property.getId());

    }

    @Test
    void getBiggestRoom_returnTheBiggestRoomDtoOnAProperty() {
        Property property = TestUtilsGenerator.getPropertyWithId();

        RoomDto returnBiggetRoomDtoExpected = TestUtilsGenerator.getBiggestRoomDto();

        RoomDto returnedRoomDto = service.getBiggestRoom(property.getId());

        assertThat(returnedRoomDto.getArea()).isPositive();
        assertThat(returnedRoomDto.getArea()).isEqualTo(returnBiggetRoomDtoExpected.getArea());
        assertThat(returnedRoomDto.getName()).isEqualTo(returnBiggetRoomDtoExpected.getName());

        Mockito.verify(repository,Mockito.atLeastOnce()).getRoom(property.getId());
    }

    @Test
    void getPropertyPriceTest(){
        Property property = TestUtilsGenerator.getPropertyWithId();

        PropertyDto returnPropertyDto = TestUtilsGenerator.getPropertyDto();
        PropertyDto propertyDto = service.getPropertyPrice(property.getId());

        assertThat(propertyDto.getPrice()).isEqualTo(returnPropertyDto.getPrice());
        assertThat(propertyDto.getName()).isEqualTo(returnPropertyDto.getName());
        assertThat(propertyDto.getRooms().get(0).getArea()).isEqualTo(returnPropertyDto.getRooms().get(0).getArea());

        Mockito.verify(service, Mockito.atLeastOnce()).getPropertyPrice(property.getId());
    }


    @Test
    void save() {
        PropertyRequestSave propertyRequestSave = TestUtilsGenerator.getNewPropertyRequestSave();

        Property propertyReturned = service.save(propertyRequestSave);

        assertThat(propertyReturned).isNotNull();
        assertThat(propertyReturned.getName()).isEqualTo(propertyReturned.getName());
        assertThat(propertyReturned.getDistrict()).isNotNull();
        assertThat(propertyReturned.getDistrict().getName()).isEqualTo(propertyReturned.getDistrict().getName());
        assertThat(propertyReturned.getDistrict().getValueM2()).isEqualTo(propertyReturned.getDistrict().getValueM2());
        assertThat(propertyReturned.getListRoom()).isNotNull();
        assertThat(propertyReturned.getListRoom()).isNotEmpty();
        assertThat(propertyReturned.getListRoom().size()).isEqualTo(propertyReturned.getListRoom().size());
        assertThat(propertyReturned.getListRoom().get(0)).isEqualTo(propertyReturned.getListRoom().get(0));
        assertThat(propertyReturned.getListRoom().get(1)).isEqualTo(propertyReturned.getListRoom().get(1));

        Mockito.verify(repository, Mockito.atLeastOnce()).save(Property.builder()
                .name(propertyRequestSave.getName())
                .district(propertyRequestSave.getDistrict())
                .listRoom(propertyRequestSave.getListRoom())
                .build());
    }

    @Test
    void getAllProperties() {
        List<Property> propertyList = TestUtilsGenerator.getPropertyListWithId();

        List<Property> propertyListReturned = service.getAllProperties();

        assertThat(propertyListReturned.size()).isEqualTo(propertyList.size());
        assertThat(propertyListReturned.get(0)).isEqualTo(propertyList.get(0));
        assertThat(propertyListReturned.get(1)).isEqualTo(propertyList.get(1));

        Mockito.verify(repository, Mockito.atLeastOnce()).getAllProperties();
    }
}