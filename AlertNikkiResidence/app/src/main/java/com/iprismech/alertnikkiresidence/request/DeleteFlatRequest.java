package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeleteFlatRequest {
    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("admin_id")
    @Expose
    public String admin_id;

    @SerializedName("flat_id")
    @Expose
    public String flat_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_id", user_id)
                .append("admin_id", admin_id)
                .append("flat_id", flat_id)
                .toString();
    }
}
