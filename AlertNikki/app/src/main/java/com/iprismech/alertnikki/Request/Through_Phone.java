package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Through_Phone {
    @SerializedName("mobile_no")
    @Expose
    public String mobileNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mobileNo", mobileNo).toString();
    }
}
