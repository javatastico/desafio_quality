package com.meli.desafio_quality.repository;

import com.meli.desafio_quality.model.Property;
import com.meli.desafio_quality.util.DataLoader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

@Repository
@Log4j2
public class PropertyRepoImpl implements PropertyRepository {

    @Override
    public Property save(Property property) {

        Property lastProperty = DataLoader.getPropertyList()
                .stream().max((p, v) -> p.getId().compareTo(v.getId())).get();

        property.setId(lastProperty.getId() + 1);

        DataLoader.getPropertyList().add(property);

        return property;
    }

    public boolean exists(Long id) {
        boolean ret = false;

        try {
            ret = DataLoader.getPropertyList().stream().anyMatch(p -> p.getId().equals(id));
        } catch (Exception e) {

        }

        return ret;
    }

}
