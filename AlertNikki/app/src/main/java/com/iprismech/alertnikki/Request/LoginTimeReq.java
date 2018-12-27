package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LoginTimeReq {
    @SerializedName("admin_id")
    @Expose
    public String admin_id;
    @SerializedName("security_id")
    @Expose
    public String security_id;
    @Override
    public String toString() {
        return new ToStringBuilder(this).append("admin_id",admin_id).append("security_id",security_id).toString();
    }
}
