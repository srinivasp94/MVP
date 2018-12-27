package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ResponseVisitMember {

    @SerializedName("visitor_id")
    @Expose
    public String visitorId;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;
    @SerializedName("in_time")
    @Expose
    public String inTime;
    @SerializedName("out_time")
    @Expose
    public String outTime;
    @SerializedName("user_name")
    @Expose
    public String userName;
    @SerializedName("flat_name")
    @Expose
    public String flatName;
    @SerializedName("building_name")
    @Expose
    public String buildingName;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("security_accept_status")
    @Expose
    public String security_accept_status;
    @SerializedName("security_name")
    @Expose
    public String security_name;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("visitorId", visitorId)
                .append("userType", userType)
                .append("userId", userId)
                .append("adminId", adminId)
                .append("date", date)
                .append("name", name)
                .append("mobile", mobile)
                .append("vehicleNo", vehicleNo)
                .append("inTime", inTime)
                .append("outTime", outTime)
                .append("userName", userName)
                .append("flatName", flatName)
                .append("buildingName", buildingName)
                .append("type", type)
                .append("security_accept_status", security_accept_status)
                .append("security_name", security_name)
                .toString();
    }


}
