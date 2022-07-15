package com.meli.desafio_quality.dto;

import com.meli.desafio_quality.model.District;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyResponseTotalPrice {
    private List<RoomDto> rooms;
    private BigDecimal price;
    private String name;
    private District district;
}


