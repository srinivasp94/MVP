package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateUserImgreq {
    @SerializedName("user_type")
    @Expose
    public String user_type;

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("user_image")
    @Expose
    public String user_image;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_type", user_type)
                .append("user_id", user_id)
                .append("user_image", user_image)
                .toString();
    }
}
