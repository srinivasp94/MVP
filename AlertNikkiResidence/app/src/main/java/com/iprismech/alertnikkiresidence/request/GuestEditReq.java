package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GuestEditReq {

    @SerializedName("guest_id")
    @Expose
    public String guestId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("guestId", guestId).append("name", name).append("vehicleNo", vehicleNo).toString();
    }

}
