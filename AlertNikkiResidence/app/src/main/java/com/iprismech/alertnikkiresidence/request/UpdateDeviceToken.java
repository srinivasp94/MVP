package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateDeviceToken {
    @SerializedName("user_id")
    @Expose
    public String user_id;
    @SerializedName("token")
    @Expose
    public String token;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_id", user_id)
                .append("token", token)
                .toString();
    }
}