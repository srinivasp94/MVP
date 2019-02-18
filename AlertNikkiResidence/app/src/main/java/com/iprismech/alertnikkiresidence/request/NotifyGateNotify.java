package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotifyGateNotify {
    @SerializedName("gate_alert_id")
    @Expose
    public String gateAlertId;
    @SerializedName("alert_status")
    @Expose
    public String alertStatus;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("gateAlertId", gateAlertId).append("alertStatus", alertStatus).toString();
    }
}
