package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotifySchoolBus {
    @SerializedName("user_schoolbus_id")
    @Expose
    public String userSchoolbusId;
    @SerializedName("status")
    @Expose
    public String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userSchoolbusId", userSchoolbusId).append("status", status).toString();
    }


}
