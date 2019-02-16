package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Vehicle {
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("vehicle_numbers")
    @Expose
    public String vehicleNumbers;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("vehicleNumbers", vehicleNumbers).toString();
    }


}
