package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Visitors_Common_Req {
    @SerializedName("visitor_id")
    @Expose
    public String visitorId;
    @SerializedName("type")
    @Expose
    public String type;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("visitorId", visitorId).append("type", type).toString();
    }
}
