package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.model.Room;
import com.meli.desafio_quality.util.DataLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Log4j2
public class PropertyRepoImpl implements PropertyRepository {

    @Autowired
    DataLoader dataLoader;

    @Override
    public Property save(Property property) {

        Property lastProperty = dataLoader.getPropertyList()
                .stream().max((p, v) -> p.getId().compareTo(v.getId())).get();

        property.setId(lastProperty.getId() + 1);

        dataLoader.getPropertyList().add(property);

        return property;
    }

    @Override
    public List<Room> getRoom(Long idProperty) {
        return dataLoader.getPropertyList().stream()
                .filter(p -> p.getId() == idProperty)
                .findFirst()
                .get()
                .getListRoom();
    }

    public boolean exists(Long id) {
        boolean ret = false;

        try {
            ret = dataLoader.getPropertyList().stream().anyMatch(p -> p.getId().equals(id));
        } catch (Exception e) {

        }

        return ret;
    }

}
