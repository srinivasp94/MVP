package com.iprismech.alertnikki.expandviews;


import com.iprismech.alertnikki.Pojo.DailyHelpsListpojo;

/**
 * Created by lenovo on 2/23/2016.
 */
public interface ItemClickListener {
    void itemClicked(DailyHelpsListpojo.ResponseBean item);
    void itemClicked(Section section);
}
