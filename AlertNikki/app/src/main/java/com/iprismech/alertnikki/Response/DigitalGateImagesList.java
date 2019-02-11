package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DigitalGateImagesList {

    @SerializedName("list_id")
    @Expose
    public String listId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("image")
    @Expose
    public String image;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("listId", listId).append("adminId", adminId).append("image", image).toString();
    }
}
