package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RequestBus {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("flat_id")
    @Expose
    public String flatId;
    @SerializedName("school_bus_name")
    @Expose
    public String schoolBusName;
    @SerializedName("driver_name")
    @Expose
    public String driverName;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("vehicle_number")
    @Expose
    public String vehicleNumber;
    @SerializedName("address")
    @Expose
    public String address;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("flatId", flatId).append("schoolBusName", schoolBusName).append("driverName", driverName).append("mobile", mobile).append("vehicleNumber", vehicleNumber).append("address", address).toString();
    }
}
