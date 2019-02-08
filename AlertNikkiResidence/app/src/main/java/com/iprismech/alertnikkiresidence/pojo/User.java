package com.iprismech.alertnikkiresidence.pojo;

public class User {
    private int imageid;
    private String name;
    private String type;

    public User(int imageid, String name, String type) {
        this.imageid = imageid;
        this.name = name;
        this.type = type;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
