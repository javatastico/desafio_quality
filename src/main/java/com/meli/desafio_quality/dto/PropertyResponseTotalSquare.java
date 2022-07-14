package com.meli.desafio_quality.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyResponseTotalSquare {
    private String name;
    private Double totalPropertySquare;

}
