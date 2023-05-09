package edu.mirea.levrost.takeandgo.takeandgo.data.models;

public class Place {
    private long id;
    private String icon;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private long radius;
    public static final int showDistance = 3000;

    public long getRadius() {
        return radius;
    }

    public Place(long id, String name, long radius, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.radius = radius;
        this.icon = "default_question_mark";
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Place(long id, String name, long radius, String icon, double latitude, double longitude) {
        this(id, name, radius,latitude, longitude);
        this.icon = icon;
    }

    public Place(long id, String name, long radius, String icon, double latitude, double longitude, String description){
        this(id, name, radius, icon, latitude, longitude);
        this.description = description;
    }

    private static final double EARTH_RADIUS = 6371.0 * 1000.0; // Earth radius in meters

    public static int calculateDistance(double userLatitude, double userLongitude, Place place) { // calculating distance in meters
        if (userLatitude != 0) { // check do we have user data
            double dLat = Math.toRadians(userLatitude - place.getLatitude());
            double dLon = Math.toRadians(userLongitude - place.getLongitude());

            double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                    Math.cos(Math.toRadians(place.getLatitude())) * Math.cos(Math.toRadians(userLatitude)) *
                            Math.sin(dLon / 2) * Math.sin(dLon / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

            return (int) (EARTH_RADIUS * c);
        }
        return 10000; // if we don't see user data return 1000, that mean distance more bigger, that we can show on map
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
