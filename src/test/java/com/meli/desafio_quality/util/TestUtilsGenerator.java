package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestUtilsGenerator {

    public static void clearList(Data propertyDb) {
        propertyDb.getPropertyList().clear();
    }

    public static Property getNewProperty() {
        return Property.builder()
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
                ).build();
    }

    public static Property getPropertyWithId() {
        return Property.builder()
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
                ).build();
    }

    public static List<Room> getRoom() {
        return Arrays.asList(
                Room.builder()
                        .name("Quarto teste 1")
                        .roomLength(9.00)
                        .roomWidth(6.00)
                        .build(),
                Room.builder()
                        .name("Quarto teste 2")
                        .roomLength(8.00)
                        .roomWidth(7.00)
                        .build());
    }

    public static List<RoomDto> getRoomDto() {
        return Arrays.asList(
                RoomDto.builder()
                        .name("Quarto teste 1")
                        .area(54.0)
                        .build(),
                RoomDto.builder()
                        .name("Quarto teste 2")
                        .area(56.0)
                        .build());
    }

    public static  RoomDto getBiggestRoomDto() {
        return RoomDto.builder()
                .name("Quarto teste 2")
                .area(56.0)
                .build();
    }

    public static PropertyDto getPropertyDto() {
        return PropertyDto.builder()
                .name("Casa teste 1")
                .district(District.builder()
                        .name("Bairro teste 1")
                        .valueM2(BigDecimal.valueOf(500.0))
                        .build()
                )
                .rooms(Arrays.asList(
                        RoomDto.builder()
                                .name("Quarto teste 1")
                                .area(54.0)
                                .build(),
                        RoomDto.builder()
                                .name("Quarto teste 2")
                                .area(56.0)
                                .build())
                ).build();
    }

    public static List<Property> getPropertyListWithId() {
        return Arrays.asList(
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
                        ).build(),
                Property.builder()
                        .id(2L)
                        .name("Casa teste 2")
                        .district(District.builder()
                                .name("Bairro teste 2")
                                .valueM2(BigDecimal.valueOf(400.0))
                                .build()
                        )
                        .listRoom(Arrays.asList(
                                Room.builder()
                                        .name("Quarto teste 1.2")
                                        .roomLength(8.00)
                                        .roomWidth(5.00)
                                        .build(),
                                Room.builder()
                                        .name("Quarto teste 2.2")
                                        .roomLength(4.00)
                                        .roomWidth(3.00)
                                        .build())
                        ).build()
        );
        }

    public static PropertyRequestSave getNewPropertyRequestSave() {
        return PropertyRequestSave.builder()
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
                ).build();
    }
}
