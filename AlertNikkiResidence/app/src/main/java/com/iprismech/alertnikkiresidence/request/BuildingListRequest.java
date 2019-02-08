package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BuildingListRequest {
    @SerializedName("admin_id")
    @Expose
    public String admin_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("admin_id",admin_id).toString();
    }
}
