package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GuestDeliveryAllowReq {

    @SerializedName("id")
    @Expose
    public String id;

    @SerializedName("allow_deny_status")
    @Expose
    public String allow_deny_status;

    @SerializedName("reson_for_deny")
    @Expose
    public String reson_for_deny;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("allow_deny_status", allow_deny_status)
                .append("reson_for_deny", reson_for_deny)
                .toString();
    }
}
