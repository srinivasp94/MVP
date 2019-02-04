package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeleteKidRequest {
    @SerializedName("user_kid_id")
    @Expose
    public String user_kid_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_kid_id", user_kid_id)
                .toString();
    }
}
