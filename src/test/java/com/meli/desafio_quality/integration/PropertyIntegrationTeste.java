package com.meli.desafio_quality.integration;

import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.dto.RoomDto;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.util.Data;
import com.meli.desafio_quality.util.DataLoaderTest;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PropertyIntegrationTeste {

    @Autowired
    TestRestTemplate testRestTemplate;

    @LocalServerPort
    private int port;

    private Data dataLoader;

    public void setup() {
        dataLoader = new DataLoaderTest();
        TestUtilsGenerator.clearList(dataLoader);
    }

    @Test
    public void getAreaRoom() {
        List<RoomDto> expectedRoomDto = TestUtilsGenerator.getRoomDto();

        String baseUrl = String.format("http://localhost:" + port + 
                "/api/v1/get-room-area/%d", 1L);

        ResponseEntity<List<RoomDto>> response =  testRestTemplate.exchange(
                baseUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RoomDto>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get(0).getName()).isEqualTo(expectedRoomDto.get(0).getName());
        assertThat(response.getBody().get(0).getArea()).isEqualTo(expectedRoomDto.get(0).getArea());
    }

    @Test
    void getPropertyArea() {
        PropertyResponseTotalSquare propertyResponseTotalSquare = TestUtilsGenerator.getResponseTotalSquare();

        String baseUrl = String.format("http://localhost:" + port +
                "/api/v1/get-property-area/%d", 1L);

        ResponseEntity<PropertyResponseTotalSquare> response =  testRestTemplate.exchange(
                baseUrl, HttpMethod.GET, null, PropertyResponseTotalSquare.class
                );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo(propertyResponseTotalSquare.getName());
        assertThat(response.getBody().getTotalPropertySquare()).isEqualTo(propertyResponseTotalSquare.getTotalPropertySquare());
    }

    @Test
    void saveProperty() {
        Property newProperty = TestUtilsGenerator.getNewProperty();

        String baseUrl = String.format("http://localhost:" + port +
                "/api/v1/property");

        HttpEntity<Property> newPropertyHttpEntity= new HttpEntity<>(newProperty);

        ResponseEntity<Property> response =  testRestTemplate.exchange(
                baseUrl, HttpMethod.POST, newPropertyHttpEntity, Property.class
        );

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().getName()).isEqualTo(newProperty.getName());
        assertThat(response.getBody().getListRoom()).isEqualTo(newProperty.getListRoom());
    }

    @Test
    void getAllProperties() {
        List<Property> properties = TestUtilsGenerator.getPropertyListWithId();

        String baseUrl = String.format("http://localhost:" + port +
                "/api/v1/property");

        ResponseEntity<List<Property>> response =  testRestTemplate.exchange(
                baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<Property>>(){});

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get(0).getId()).isEqualTo(properties.get(0).getId());
        assertThat(response.getBody().get(0).getDistrict().getValueM2()).isEqualTo(properties.get(0).getDistrict().getValueM2());
    }
}
