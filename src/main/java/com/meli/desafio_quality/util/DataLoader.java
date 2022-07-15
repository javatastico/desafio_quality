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
public class DataLoader extends Data{

    private final List<Property> propertyDb = new ArrayList<>();

    public DataLoader() {
        this.propertyDb.add(
                Property.builder()
                        .id(1L)
                        .name("Casa 1")
                        .district(District.builder()
                                .name("Bairro 1")
                                .valueM2(BigDecimal.valueOf(100.0))
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
                                        .roomLength(5.00)
                                        .roomWidth(4.00)
                                        .build())
                        ).build());

    }

    public List<Property> getPropertyList() {
        return propertyDb;

    }
}
