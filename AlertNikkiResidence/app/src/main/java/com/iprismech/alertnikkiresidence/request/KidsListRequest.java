package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class KidsListRequest {
    @SerializedName("user_type")
    @Expose
    public String user_type;
    @SerializedName("user_id")
    @Expose
    public String user_id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_type", user_type)
                .append("user_id", user_id)
                .append("adminId", adminId)
                .toString();
    }
}
