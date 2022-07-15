package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class District {

    @NotEmpty(message = "District name cannot be empty.")
    @Pattern(message = "The name of the room must begin with a capital letter",
            regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ/s]+")
    @Size(max = 45, message = "District name can't exceed 45 characters.")
    private String name;

    @NotNull(message = "District value cannot be empty.")
    @Digits(message = "Value cannot exceed 13 characters", integer = 11, fraction = 2)
    private BigDecimal valueM2;

}
