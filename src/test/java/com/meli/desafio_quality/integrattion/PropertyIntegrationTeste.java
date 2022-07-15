package com.meli.desafio_quality.integrattion;

import com.meli.desafio_quality.dto.RoomDto;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.repository.PropertyRepoImpl;
import com.meli.desafio_quality.util.Data;
import com.meli.desafio_quality.util.DataLoaderTest;
import com.meli.desafio_quality.util.TestUtilsGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

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
        Property newProperty = TestUtilsGenerator.getNewProperty();
        PropertyRepoImpl propertyRepo = new PropertyRepoImpl();
        propertyRepo.save(newProperty);
        String baseUrl = String.format("http://localhost:" + port + 
                "/api/v1/get-room-area/%d", newProperty.getId());


        ResponseEntity<List<RoomDto>> response =  testRestTemplate.exchange(
                baseUrl, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<RoomDto>>() {
                });


        System.out.println(response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().get(0).getName()).isEqualTo(expectedRoomDto.get(0).getName());


    }
}
