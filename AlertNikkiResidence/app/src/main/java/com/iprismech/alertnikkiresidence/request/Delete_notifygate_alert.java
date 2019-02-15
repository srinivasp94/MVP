package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Delete_notifygate_alert {
    //    "gate_alert_id":"6",
    @SerializedName("gate_alert_id")
    @Expose
    public String gate_alert_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("gate_alert_id", gate_alert_id)
                .toString();
    }
}
