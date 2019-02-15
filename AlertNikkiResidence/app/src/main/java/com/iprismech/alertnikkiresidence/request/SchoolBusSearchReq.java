package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SchoolBusSearchReq {
    @SerializedName("school_bus_name")
    @Expose
    public String school_bus_name;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("school_bus_name", school_bus_name)
                .append("adminId", adminId)
                .toString();
    }
}
