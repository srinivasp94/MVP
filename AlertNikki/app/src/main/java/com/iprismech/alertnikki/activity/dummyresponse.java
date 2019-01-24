package com.iprismech.alertnikki.activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class dummyresponse {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public List<Responselist> response = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }

    public class Responselist {

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
        @SerializedName("primary_number")
        @Expose
        public String primaryNumber;
        @SerializedName("secondary_number")
        @Expose
        public String secondaryNumber;
        @SerializedName("type")
        @Expose
        public String type;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("visitorId", visitorId).append("userType", userType).append("userId", userId).append("adminId", adminId).append("date", date).append("name", name).append("mobile", mobile).append("vehicleNo", vehicleNo).append("inTime", inTime).append("outTime", outTime).append("userName", userName).append("flatName", flatName).append("buildingName", buildingName).append("primaryNumber", primaryNumber).append("secondaryNumber", secondaryNumber).append("type", type).toString();
        }
    }

}
