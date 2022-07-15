package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.exception.ExceptionDetails;
import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.util.Data;
import com.meli.desafio_quality.util.DataLoader;
import com.meli.desafio_quality.util.DataLoaderTest;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.list;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void getAllProperties_returnAPropertyList_whenListExists() {
        List<Property> propertyList = TestUtilsGenerator.getPropertyListWithId();

        repository.save(propertyList.get(0));
        repository.save(propertyList.get(1));

        List<Property> propertyListReturned = repository.getAllProperties();

        assertThat(propertyListReturned.size()).isEqualTo(propertyList.size());
        assertThat(propertyListReturned.get(0)).isEqualTo(propertyList.get(0));
        assertThat(propertyListReturned.get(1)).isEqualTo(propertyList.get(1));
    }

    @Test
    void getAllProperties_returnAPropertyList_whenListNotExists() {
        List<Property> propertyList = new ArrayList<>();

        List<Property> propertyListReturned = repository.getAllProperties();

        assertThat(propertyListReturned.size()).isEqualTo(propertyList.size());
    }
}