package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MaidAttendanceHistoryReq {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("maid_id")
    @Expose
    public String maidId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("maidId", maidId).toString();
    }
}
