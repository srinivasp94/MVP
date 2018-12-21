package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AllowAdminStaff {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("staff_id")
    @Expose
    public String staffId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("staffId", staffId).toString();
    }
}
