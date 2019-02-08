package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotifyGateAdd {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("service_id")
    @Expose
    public String serviceId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("vaild_to")
    @Expose
    public String vaildTo;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("userType", userType).append("userId", userId).append("serviceId", serviceId).append("name", name).append("mobile", mobile).append("vaildTo", vaildTo).toString();
    }
}
