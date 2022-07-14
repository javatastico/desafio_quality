package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.RoomDto;
import com.meli.desafio_quality.service.PropertyService;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.assertj.core.api.Assertions;
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
import static org.junit.jupiter.api.Assertions.*;

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
}