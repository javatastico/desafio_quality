package com.meli.desafio_quality.util;

import com.meli.desafio_quality.model.Property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Data {
    private final List<Property> propertyDb = new ArrayList<>();

    public abstract List<Property> getPropertyList();

    public Data() {
    }
}
