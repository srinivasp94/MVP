package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StaffProfileRequest {
    @SerializedName("maid_id")
    @Expose
    public String maidId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("maidId", maidId)
                .append("adminId", adminId)
                .toString();
    }
}
