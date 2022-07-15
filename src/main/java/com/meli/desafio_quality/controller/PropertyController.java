package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.dto.PropertyResponseTotalPrice;
import com.meli.desafio_quality.dto.PropertyResponseTotalSquare;
import com.meli.desafio_quality.dto.RoomDto;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.dto.PropertyRequestSave;
import com.meli.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.meli.desafio_quality.exception.NotFoundException;

import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService service;

    /**
     * Get area room
     *
     * @param id - Property id.
     * @return An area and name of room, return an exception if property isn't find.
     * @throws NotFoundException When a property doesn't exists.
     * @see <a href="http://localhost:8080/api/vi/get-room-area/{id}">Get area room</a>
     */
    @GetMapping("/get-room-area/{id}")
    public ResponseEntity<List<RoomDto>> getAreaRoom(@PathVariable Long id){
        return new ResponseEntity<>(service.getAreaRoom(id),HttpStatus.OK);
    }

    /**
     * Get area property
     *
     * @param id - Property id.
     * @return An area and name of property, return an exception if property isn't find.
     * @throws NotFoundException When a property doesn't exists.
     * @see <a href="http://localhost:8080/api/vi/get-property-area/{id}">Get area property</a>
     */
    @GetMapping("/get-property-area/{id}")
    public ResponseEntity<List<PropertyResponseTotalSquare>> getPropertyArea(@PathVariable Long id){
        return new ResponseEntity(service.getPropertyArea(id),HttpStatus.OK);
    }

    /**
     * Get the biggest room of a property
     *
     * @param id - Property id.
     * @return The biggest room of a property, return an exception if property isn't find.
     * @throws NotFoundException When a property doesn't exists.
     * @see <a href="http://localhost:8080/api/vi/get-biggest-room/{id}">Get the biggest room</a>
     */
    @GetMapping("/get-biggest-room/{id}")
    public ResponseEntity<RoomDto> getBiggestRoom(@PathVariable Long id){
        return new ResponseEntity<>(service.getBiggestRoom(id),HttpStatus.OK);
    }

    /**
     * Get total price of the property and its attributes
     *
     * @param id - Property id.
     * @return The biggest room of a property, return an exception if property isn't find.
     * @throws NotFoundException When a property doesn't exists.
     * @see <a href="http://localhost:8080/api/vi/get-biggest-room/{id}">Get the biggest room</a>
     */
    @GetMapping("/get-property-price/{id}")
    public ResponseEntity<PropertyResponseTotalPrice> getPropertyPrice(@PathVariable Long id){
        return new ResponseEntity(service.getPropertyPrice(id),HttpStatus.OK);
    }

    @PostMapping("/property")
    public ResponseEntity<Property> saveProperty(@RequestBody @Valid PropertyRequestSave propertyRequestSave) {
        return new ResponseEntity<>(service.save(propertyRequestSave), HttpStatus.CREATED);
    }

    @GetMapping("/property")
    public ResponseEntity<List<Property>> getAllProperties() {
        return new ResponseEntity<>(service.getAllProperties(), HttpStatus.OK);
    }
}
