package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SchoolHistoryReq {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("schoolbus_id")
    @Expose
    public String schoolbusId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("schoolbusId", schoolbusId).toString();
    }

}
