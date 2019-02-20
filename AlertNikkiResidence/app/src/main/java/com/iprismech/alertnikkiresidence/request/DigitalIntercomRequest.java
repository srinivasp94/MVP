package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DigitalIntercomRequest {
    @SerializedName("digital_intercom_id")
    @Expose
    public String digital_intercom_id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("digital_intercom_id", digital_intercom_id)
                .append("adminId", adminId)
                .append("userType", userType)
                .append("userId", userId)
                .toString();
    }
}
