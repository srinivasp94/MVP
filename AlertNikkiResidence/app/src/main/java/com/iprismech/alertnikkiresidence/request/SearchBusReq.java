package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchBusReq {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("school_bus_name")
    @Expose
    public String schoolBusName;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("schoolBusName", schoolBusName).toString();
    }
}
