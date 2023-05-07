package edu.mirea.levrost.takeandgo.takeandgo.data.models;

public class Profile {
    private String icon;
    private String name;
    private int rating;
    private String id;

    public Profile(String name, int rating, String id) {
        this.name = name;
        this.rating = rating;
        this.id = id;
        this.icon = "default_question_mark";
    }

    public Profile(String name, String icon, int rating, String id) {
        this(name, rating, id);
        this.icon = icon;
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

    public String getId() {
        return id;
    }
}
