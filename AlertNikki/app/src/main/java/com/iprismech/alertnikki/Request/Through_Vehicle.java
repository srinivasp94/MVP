package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Through_Vehicle {

    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("vehicleNo", vehicleNo).toString();
    }
}
