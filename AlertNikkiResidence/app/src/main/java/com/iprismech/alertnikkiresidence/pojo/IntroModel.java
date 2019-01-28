package com.iprismech.alertnikkiresidence.pojo;

/**
 * Created by rk on 06-Dec-18.
 */

public class IntroModel {
    public int images;
    public String Title, descript;

    public IntroModel() {
    }

    public IntroModel(int images, String title, String descript) {
        this.images = images;
        Title = title;
        this.descript = descript;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
