package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SaveSchoolBusRoute {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("school_bus_id")
    @Expose
    public String schoolBusId;
    @SerializedName("route_id")
    @Expose
    public String routeId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("userType", userType).append("userId", userId).append("schoolBusId", schoolBusId).append("routeId", routeId).toString();
    }
}
