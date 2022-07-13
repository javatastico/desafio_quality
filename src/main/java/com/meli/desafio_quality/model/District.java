package com.meli.desafio_quality.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class District {

    @NotBlank(message = "District name cannot be empty.")
    @Size(max = 45, message = "District name can't exceed 45 characters.")
    private String name;

    @NotBlank(message = "District value cannot be empty.")
    @DecimalMax(message = "Value cannot exceed 13 characters", value = "13")
    @DecimalMin(message = "Value should be greater then 0", value = "0", inclusive = false)
    private Double valueM2;

}
