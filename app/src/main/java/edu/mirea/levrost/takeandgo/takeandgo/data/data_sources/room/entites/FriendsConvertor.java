package edu.mirea.levrost.takeandgo.takeandgo.data.data_sources.room.entites;

import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FriendsConvertor {

    @TypeConverter
    public static String fromIdOfFriends(List<String> value) {
        String output = "";
        for (String place : value) {
            output = output + place + ",";
        }
        return output;
    }

    @TypeConverter
    public static List<String> toIdOfFriends(String data) {
        return Arrays.stream(data.split(",")).collect(Collectors.toList());
    }
}
