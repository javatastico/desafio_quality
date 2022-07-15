package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyRequestSave {

    @NotBlank(message = "Property name cannot be empty.")
    @Pattern(regexp = "[A-Z][a-záàâãéèêíïóôõöúçñ/s]+", message = "Property name should start with upper case.")
    @Size(max = 30, message = "Property name cannot exceed 30 characters")
    private String name;

    @Valid
    @NotNull(message = "District cannot  be empty.")
    private District district;

    @NotEmpty(message = "Room list cannot be empty.")
    private List<@Valid Room> listRoom;

}
