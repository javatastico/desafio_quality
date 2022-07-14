package com.meli.desafio_quality.controller;

import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.PropertyDto;
import com.meli.desafio_quality.model.RoomDto;
import com.meli.desafio_quality.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/get-area-property/{id}")
    public ResponseEntity<List<PropertyDto>> getPropertyArea(@PathVariable Long id){
        return new ResponseEntity(service.getPropertyArea(id),HttpStatus.OK);
    }

    @GetMapping("/get-biggest-room/{id}")
    public ResponseEntity<RoomDto> getBiggestRoom(@PathVariable Long id){
        return new ResponseEntity<>(service.getBiggestRoom(id),HttpStatus.OK);
    }

    @GetMapping("/get-property-price/{id}")
    public ResponseEntity<PropertyDto> getPropertyPrice(@PathVariable Long id){
        return new ResponseEntity<>(service.getPropertyPrice(id),HttpStatus.OK);
    }


}
