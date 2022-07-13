package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataLoader {

    private final static List<Property> propertyDb = new ArrayList<>();

    DataLoader() {
        this.propertyDb.add(
                Property.builder()
                        .id(1L)
                        .name("Casa 1")
                        .district(District.builder()
                                .name("Bairro 1")
                                .valueM2(100.0)
                                .build()
                        )
                        .listRoom(Arrays.asList(
                                Room.builder()
                                        .name("Quarto 1")
                                        .roomLength(4.00)
                                        .roomWidth(3.00)
                                        .build(),
                                Room.builder()
                                        .name("Quarto 2")
                                        .roomLength(4.00)
                                        .roomWidth(3.00)
                                        .build())
                        ).build());

    }

    public static List<Property> getPropertyList() {
        return propertyDb;

    }
}
