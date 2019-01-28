package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GuestsReq {
    @SerializedName("otp_sent_type")
    @Expose
    public String otpSentType;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public int userId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("otpSentType", otpSentType).append("userType", userType).append("userId", userId).append("adminId", adminId).toString();
    }
}
