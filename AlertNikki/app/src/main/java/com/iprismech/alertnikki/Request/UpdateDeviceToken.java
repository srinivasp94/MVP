package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateDeviceToken {
    @SerializedName("security_id")
    @Expose
    public String security_id;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("ios_token")
    @Expose
    public String ios_token;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("security_id", security_id).append("token", token).append("ios_token", ios_token).toString();
    }
}
