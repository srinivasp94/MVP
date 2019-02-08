package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SchoolBusList {
    @SerializedName("user_schoolbus_id")
    @Expose
    public String userSchoolbusId;
    @SerializedName("notification_status")
    @Expose
    public String notificationStatus;
    @SerializedName("school_bus_name")
    @Expose
    public String schoolBusName;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("entry_time")
    @Expose
    public String entryTime;
    @SerializedName("exit_time")
    @Expose
    public String exitTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userSchoolbusId", userSchoolbusId).append("notificationStatus", notificationStatus).append("schoolBusName", schoolBusName).append("address", address).append("entryTime", entryTime).append("exitTime", exitTime).toString();
    }
}
