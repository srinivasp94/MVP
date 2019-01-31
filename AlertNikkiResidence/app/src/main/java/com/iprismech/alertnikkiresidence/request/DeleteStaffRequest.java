package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeleteStaffRequest {

    @SerializedName("user_maid_id")
    @Expose
    public String user_maid_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_maid_id", user_maid_id)
                .toString();
    }
}
