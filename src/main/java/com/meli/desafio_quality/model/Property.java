package com.meli.desafio_quality.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Property {

    private Long id;
    @NotBlank(message = "Property name cannot be empty.")
    @Pattern(regexp = "([A-Z]{1}[a-z]+\\s??)+", message = "Property name should start with upper case.")
    @Size(max = 30, message = "Property name cannot exceed 30 characters")
    private String name;

    @NotBlank(message = "District cannot  be empty.")
    @Valid
    private District district;

    @Valid
    @NotEmpty(message = "Room list cannot be empty.")
    private List<Room> listRoom;

}
