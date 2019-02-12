package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SchoolBus_Out {
    @SerializedName("attendence_id")
    @Expose
    public String attendenceId;
    @SerializedName("school_bus_id")
    @Expose
    public String schoolBusId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

    @SerializedName("route_id")
    @Expose
    public String route_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("attendenceId", attendenceId)
                .append("schoolBusId", schoolBusId)
                .append("adminId", adminId)
                .append("route_id", route_id)
                .toString();
    }
}
