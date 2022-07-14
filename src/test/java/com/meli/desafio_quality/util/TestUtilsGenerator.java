package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;

import java.util.Arrays;
import java.util.List;

public class TestUtilsGenerator {

    public static void clearList(Data propertyDb) {
        propertyDb.getPropertyList().clear();
    }

    public static Property getNewProperty() {
        return Property.builder()
                .id(1L)
                .name("Casa teste 1")
                .district(District.builder()
                        .name("Bairro teste 1")
                        .valueM2(500.0)
                        .build()
                )
                .listRoom(Arrays.asList(
                        Room.builder()
                                .name("Quarto teste 1")
                                .roomLength(9.00)
                                .roomWidth(6.00)
                                .build(),
                        Room.builder()
                                .name("Quarto teste 2")
                                .roomLength(8.00)
                                .roomWidth(7.00)
                                .build())
                ).build();
    }

}
