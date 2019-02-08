package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DigitalntercomSettingsRequest {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("primary_number")
    @Expose
    public String primaryNumber;
    @SerializedName("secondary_number")
    @Expose
    public String secondaryNumber;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("userType", userType).append("userId", userId).append("primaryNumber", primaryNumber).append("secondaryNumber", secondaryNumber).toString();
    }
}
