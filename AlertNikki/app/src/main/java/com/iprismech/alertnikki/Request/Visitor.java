package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Visitor {

    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("search")
    @Expose
    public String search;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("status", status).append("search", search).toString();
    }
}
