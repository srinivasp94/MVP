package com.iprismech.alertnikki.Response;

public class FoodModel {
    private String title;
    private int img_id;

    public FoodModel(String title, int img_id) {
        this.title = title;
        this.img_id = img_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}
