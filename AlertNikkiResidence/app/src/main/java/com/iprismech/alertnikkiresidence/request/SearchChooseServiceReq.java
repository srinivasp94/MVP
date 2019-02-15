package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SearchChooseServiceReq {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("service_name")
    @Expose
    public String service_name;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("adminId", adminId)
                .append("service_name", service_name)
                .toString();
    }
}
