package com.meli.desafio_quality.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

public class Property {

    @NotBlank(message = "O nome da propriedade não pode estar vazio.")
    @Pattern(regexp = "([A-Z]{1}[a-z]+\\s??)+", message = "O nome da propriedade deve começar com uma letra maiúscula")
    @Size(max = 30, message = "O comprimento do nome não pode exceder 30 caracters")
    private String name;

    @NotBlank(message = "O bairro não pode estar vazio.")
    @Valid
    private District district;

    @Valid
    @NotEmpty(message = "A lista de cômodos não pode estar vazia.")
    private List<Room> listRoom;

}
