package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AlertModel {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("digital_gatepass_alerts")
    @Expose
    public List<DigitalGatepassAlert> digitalGatepassAlerts = null;
    @SerializedName("notify_gate_alerts")
    @Expose
    public List<NotifiGateAlerts> notifyGateAlerts = null;
    @SerializedName("kids_gate_alerts")
    @Expose
    public List<KidsGateAlerts> kidsGateAlerts = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("digitalGatepassAlerts", digitalGatepassAlerts).append("notifyGateAlerts", notifyGateAlerts).append("kidsGateAlerts", kidsGateAlerts).toString();
    }

}
