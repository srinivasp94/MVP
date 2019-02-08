package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class KidGatePassRequest {
    @SerializedName("user_kid_id")
    @Expose
    public String user_kid_id;
    @SerializedName("kid_going_with_whom")
    @Expose
    public String kid_going_with_whom;
    @SerializedName("kid_image")
    @Expose
    public String kid_image;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_kid_id", user_kid_id)
                .append("kid_going_with_whom", kid_going_with_whom)
                .append("kid_image", kid_image)
                .toString();
    }
}
