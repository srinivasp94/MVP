package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Through_Otp {
    @SerializedName("otp")
    @Expose
    public String otp;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("otp", otp).toString();
    }

}
