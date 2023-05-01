package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.TypeConverter;

import java.util.Arrays;
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
        return Arrays.stream(data.split(",")).map(i -> Long.valueOf(i)).collect(Collectors.toList());
    }
}
