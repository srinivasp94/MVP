package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Visitor_In {
    @SerializedName("visitor_id")
    @Expose
    public String visitorId;
    @SerializedName("security_id")
    @Expose
    public int securityId;
    @SerializedName("type")
    @Expose
    public String type;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("visitorId", visitorId).append("securityId", securityId).append("type", type).toString();
    }
}
