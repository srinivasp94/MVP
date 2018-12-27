package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SchoolBusNotify {

    @SerializedName("school_bus_id")
    @Expose
    public String schoolBusId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("schoolBusId", schoolBusId).append("adminId", adminId).toString();
    }
}
