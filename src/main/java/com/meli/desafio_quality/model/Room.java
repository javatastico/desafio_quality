package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @NotEmpty(message = "The field cannot be empty.")
    @Pattern(message = "The name of the room must begin with a capital letter",
            regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ/s]+")
    @Size(message = "The length of the room cannot exceed 30 characters",
            max = 30)
    private String name;

    @NotNull(message = "The width of the room cannot be empty.")
    @DecimalMax(message = "Value cannot exceed 25 characters", value = "25.0")
    @DecimalMin(message = "Value should be greater then 0", value = "0",
            inclusive = false
    )
    private Double roomWidth;

    @NotNull(message = "The width of the room cannot be empty.")
    @DecimalMax(message = "Value cannot exceed 33 characters", value = "33.0")
    @DecimalMin(message = "Value should be greater then 0", value = "0",
            inclusive = false
    )
    private Double roomLength;

}
