package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlacesConvertor {

    @TypeConverter
    public static String fromIdOfVisitedPlaces(List<Long> value) {
        String output = "";
        for (long place : value) {
            output = output + String.valueOf(place) + ",";
        }
        return output;
    }

    @TypeConverter
    public static List<Long> toIdOfVisitedPlaces(String data) {
        if (data == null || data.isEmpty()){
            return Collections.emptyList();
        }
        return Arrays.stream(data.split(",")).map(Long::valueOf).collect(Collectors.toList());
    }
}
