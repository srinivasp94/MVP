package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DeliveryBoy_Req {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("building_id")
    @Expose
    public String buildingId;
    @SerializedName("flat_id")
    @Expose
    public String flatId;
    @SerializedName("delivery_from")
    @Expose
    public String deliveryFrom;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;
    @SerializedName("photo")
    @Expose
    public String photo;
    @SerializedName("service_from")
    @Expose
    public String service_from;

    @SerializedName("added_from")
    @Expose
    public String added_from;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("adminId", adminId)
                .append("name", name)
                .append("mobile", mobile)
                .append("buildingId", buildingId)
                .append("flatId", flatId)
                .append("deliveryFrom", deliveryFrom)
                .append("vehicleNo", vehicleNo)
                .append("photo", photo)
                .append("service_from", service_from)
                .append("added_from", added_from)
                .toString();
    }
}
