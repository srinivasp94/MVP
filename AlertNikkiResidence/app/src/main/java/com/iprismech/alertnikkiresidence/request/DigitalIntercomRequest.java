package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DigitalIntercomRequest {
    @SerializedName("digital_intercom_id")
    @Expose
    public String digital_intercom_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("digital_intercom_id", digital_intercom_id)
                .toString();
    }
}
