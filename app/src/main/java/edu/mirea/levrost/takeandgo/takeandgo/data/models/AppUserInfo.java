package edu.mirea.levrost.takeandgo.takeandgo.data.models;

public class AppUserInfo {
    private String name;
    private int userId;

    public AppUserInfo(){
        this.name = "";
        this.userId = 0;
    }

    public AppUserInfo(String name, int userId){
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return this.name;
    }

    public boolean isLogin() {
        if (!(name.equals("") || userId == 0)){
            return true;
        }
        else {
            return false;
        }
    }

    public int getUserId() {
        return this.userId;
    }
}
