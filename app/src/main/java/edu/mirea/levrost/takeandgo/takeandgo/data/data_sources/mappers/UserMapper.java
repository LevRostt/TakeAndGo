package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.mappers;

import edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites.UserDataEntity;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.Profile;
import edu.mirea.levrost.takeandgo.takeandgo.data.models.UserData;

public class UserMapper {

    public static UserData toDomainModel(UserDataEntity entity){
        return new UserData(entity.name, entity.userId, entity.idOfVisitedPlaces ,entity.latitude, entity.longitude);
    }

}
