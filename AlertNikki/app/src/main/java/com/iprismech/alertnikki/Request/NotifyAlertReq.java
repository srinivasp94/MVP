package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotifyAlertReq {
    @SerializedName("notify_gate_id")
    @Expose
    public String notify_gate_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("notify_gate_id", notify_gate_id)
                .toString();
    }
}
