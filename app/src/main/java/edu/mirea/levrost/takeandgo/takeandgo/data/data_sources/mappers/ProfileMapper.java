package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.ProfileEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;

public class ProfileMapper {

    public static Profile toDomainModel(ProfileEntity entity){
        return new Profile(entity.name, entity.icon, entity.rating, entity.uId);
    }

}
