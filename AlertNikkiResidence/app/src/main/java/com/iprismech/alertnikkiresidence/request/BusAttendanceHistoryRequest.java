package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BusAttendanceHistoryRequest {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("schoolbus_id")
    @Expose
    public String schoolbus_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("schoolbus_id", schoolbus_id).toString();
    }
}
