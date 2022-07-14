package com.meli.desafio_quality.service;

import com.meli.desafio_quality.model.Property;
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

import java.util.List;

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
}