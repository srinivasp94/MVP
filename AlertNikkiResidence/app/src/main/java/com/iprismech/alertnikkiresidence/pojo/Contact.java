package com.iprismech.alertnikkiresidence.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Contact {


    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("mobile", mobile).append("vehicleNo", vehicleNo).toString();
    }
}


