package edu.mirea.levrost.takeandgo.takeandgo.data.models;

public class Profile {
    private String icon;
    private String name;
    private int rating;
    private int id;

    public Profile(String name, String icon, int rating, int id) {
        this.icon = icon;
        this.name = name;
        this.rating = rating;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public int getRating() {
        return rating;
    }

    public int getId() {
        return id;
    }
}
