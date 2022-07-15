package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.PropertyRequestSave;
import com.meli.desafio_quality.model.RoomDto;
import com.meli.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @GetMapping("/get-area-room/{id}")
    public ResponseEntity<List<RoomDto>> getAreaRoom(@PathVariable Long id){
        return new ResponseEntity<>(service.getAreaRoom(id),HttpStatus.OK);
    }

    @GetMapping("/get-biggest-room/{id}")
    public ResponseEntity<RoomDto> getBiggestRoom(@PathVariable Long id){
        return new ResponseEntity<>(service.getBiggestRoom(id),HttpStatus.OK);
    }

    @GetMapping("/get-property-price/{id}")
    public ResponseEntity<PropertyDto> getPropertyPrice(@PathVariable Long id){
        return new ResponseEntity<>(service.getPropertyPrice(id),HttpStatus.OK);
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
