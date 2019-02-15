package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ForgotPasswordRequest {
    @SerializedName("mobile")
    @Expose
    public String mobile;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("mobile", mobile)
                .toString();
    }
}
