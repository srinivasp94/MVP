package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResetPasswordRequest {
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("user_id")
    @Expose
    public String userId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("password", password)
                .append("userId", userId)
                .toString();
    }
}
