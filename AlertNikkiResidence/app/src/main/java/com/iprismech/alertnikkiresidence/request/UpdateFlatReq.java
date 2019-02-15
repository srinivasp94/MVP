package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateFlatReq {
    @SerializedName("flat_id")
    @Expose
    public String flat_id;
    @SerializedName("user_id")
    @Expose
    public String userId;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("flat_id", flat_id)
                .append("userId", userId)
                .toString();
    }
}
