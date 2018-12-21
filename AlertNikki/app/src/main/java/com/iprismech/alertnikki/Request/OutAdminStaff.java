package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OutAdminStaff {
    @SerializedName("attendence_id")
    @Expose
    public String attendenceId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("attendenceId", attendenceId).toString();
    }

}
