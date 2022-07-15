package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoaderTest extends Data{

    private final List<Property> propertyDb = new ArrayList<>();

    public DataLoaderTest() {
        this.propertyDb.add(
                Property.builder()
                        .id(1L)
                        .name("Casa teste 1")
                        .district(District.builder()
                                .name("Bairro teste 1")
                                .valueM2(BigDecimal.valueOf(500.0))
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
                        ).build());
    }

    public List<Property> getPropertyList() {
        return propertyDb;

    }
}
