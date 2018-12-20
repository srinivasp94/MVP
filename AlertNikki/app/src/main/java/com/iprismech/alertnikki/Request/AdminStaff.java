package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdminStaff {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("search")
    @Expose
    public String search;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("search", search).toString();
    }

}
