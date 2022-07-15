package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.util.Data;
import com.meli.desafio_quality.util.DataLoaderTest;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PropertyRepoImplTest {

    private PropertyRepository repository;
    private Data dataLoader;

    @BeforeEach
    public void setup(){
        repository = new PropertyRepoImpl();
        dataLoader = new DataLoaderTest();
        TestUtilsGenerator.clearList(dataLoader);
    }

    @Test
    void getRoom_returnListRoom_whenPropertyExists() {
        Property property = TestUtilsGenerator.getNewProperty();
        Property savedProperty = repository.save(property);

        List<Room> listRoom = repository.getRoom(savedProperty.getId());

        assertThat(listRoom).isNotEmpty();

        assertThat(listRoom.get(0).getName())
                .isEqualTo(savedProperty.getListRoom().get(0).getName());

        assertThat(listRoom.size())
                .isEqualTo(savedProperty.getListRoom().size());
    }

    @Test
    void getRoom_returnExceptionNotFound_whenPropertyNotExists() {
        Property property = TestUtilsGenerator.getNewProperty();

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            repository.getRoom(property.getId());
        });

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getMessage()).isEqualTo("Property not found.");
    }

    @Test
    void getProperty_returnProperty_whenPropertyExists() {
        Property property = TestUtilsGenerator.getPropertyWithId();
        Property savedProperty = repository.save(property);

        Property returnedProperty = repository.getProperty(savedProperty.getId());

        assertThat(returnedProperty).isNotNull();

        assertThat(returnedProperty.getId()).isEqualTo(savedProperty.getId());
        assertThat(returnedProperty.getName()).isEqualTo(savedProperty.getName());
        assertThat(returnedProperty.getDistrict()).isEqualTo(savedProperty.getDistrict());
        assertThat(returnedProperty.getListRoom()).isEqualTo(savedProperty.getListRoom());

    }

    @Test
    void getProperty_returnNotFoundException_whenPropertyNotExists() {
        Property property = TestUtilsGenerator.getPropertyWithId();

        NotFoundException exception = Assertions.assertThrows(NotFoundException.class, () -> {
            repository.getProperty(property.getId());
        });

        assertThat(exception.getStatus()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(exception.getMessage()).isEqualTo("Property not found.");

    }
}