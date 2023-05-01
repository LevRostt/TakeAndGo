package edu.mirea.levrost.takeandgo.takeandgo.data.models;

public class Place {
    private long id;
    private String icon;
    private String name;
    private double latitude;
    private double longitude;
    private String description;

    public Place(long id, String name,  double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.icon = "default_question_mark";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(long id, String name, String icon, double latitude, double longitude) {
        this(id, name, latitude, longitude);
        this.icon = icon;
    }

    public Place(long id, String name, String icon, double latitude, double longitude, String description){
        this(id, name, icon, latitude, longitude);
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescription() {
        return description;
    }

    public long getId() {
        return id;
    }
}
