package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.District;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DataLoaderTest extends Data{

    private final List<Property> propertyDb = new ArrayList<>();

    public DataLoaderTest() {

    }

    public List<Property> getPropertyList() {
        return propertyDb;

    }
}
