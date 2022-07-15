package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.util.Data;
import com.meli.desafio_quality.util.DataLoader;
import com.meli.desafio_quality.util.DataLoaderTest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Properties;

@Repository
@Log4j2
public class PropertyRepoImpl implements PropertyRepository {

    private Data dataLoader;

    private String scope;

    public PropertyRepoImpl() {
        Properties properties = new Properties();

        try {
            properties.load(new ClassPathResource("application.properties").getInputStream());
            this.scope = properties.getProperty("api.scope");
        } catch (Exception e) {

        }

        if(this.scope.equals("main")) {
            this.dataLoader = new DataLoader();
        } else {
            this.dataLoader = new DataLoaderTest();
        }
    }

    @Override
    public Property save(Property property) {

        if (dataLoader.getPropertyList().isEmpty()) {
            property.setId(1L);
        } else {
            Property lastProperty = dataLoader.getPropertyList()
                    .stream().max((p, v) -> p.getId().compareTo(v.getId())).get();
            property.setId(lastProperty.getId() + 1);
        }

        dataLoader.getPropertyList().add(property);

        return property;
    }

    @Override
    public List<Room> getRoom(Long idProperty) {
        System.out.println("Scope: " + scope);
        System.out.println("Data loader: " + dataLoader.getPropertyList());
        try {
            return dataLoader.getPropertyList().stream()
                    .filter(p -> p.getId() == idProperty)
                    .findFirst()
                    .get()
                    .getListRoom();
        } catch (Exception e) {
            throw new NotFoundException("Property not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Property getProperty(Long idProperty) {
        try {
            return dataLoader.getPropertyList()
                    .stream()
                    .filter(p -> p.getId() == idProperty)
                    .findFirst()
                    .get();
        } catch (Exception e) {
            throw new NotFoundException("Property not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Property> getAllProperties() {
        return dataLoader.getPropertyList();
    }

}
