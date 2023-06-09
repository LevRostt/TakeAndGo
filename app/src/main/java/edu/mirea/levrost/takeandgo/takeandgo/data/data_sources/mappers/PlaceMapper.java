package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers;

import org.w3c.dom.Entity;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.PlaceEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Place;

public class PlaceMapper {

    public static Place toDomainModel(PlaceEntity entity){
        if (entity != null)
            return new Place(entity.id, entity.name, entity.radius, entity.icon, entity.latitude, entity.longitude);
        return null;
    }

}
