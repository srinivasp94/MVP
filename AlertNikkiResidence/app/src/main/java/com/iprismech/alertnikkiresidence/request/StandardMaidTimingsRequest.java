package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StandardMaidTimingsRequest {
    @SerializedName("maid_id")
    @Expose
    public String service_id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("service_id", service_id)
                .append("adminId", adminId)
                .toString();
    }
}
