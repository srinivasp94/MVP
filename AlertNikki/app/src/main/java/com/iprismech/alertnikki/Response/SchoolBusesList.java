package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SchoolBusesList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("flat_id")
    @Expose
    public String flatId;
    @SerializedName("request_type")
    @Expose
    public String requestType;
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
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("delete_status")
    @Expose
    public String deleteStatus;
    @SerializedName("attendence")
    @Expose
    public  BusSchedule attendence = null;

    @SerializedName("route_id")
    @Expose
    public  String route_id;
    @SerializedName("route")
    @Expose
    public  String route;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("adminId", adminId)
                .append("flatId", flatId)
                .append("requestType", requestType)
                .append("schoolBusName", schoolBusName)
                .append("driverName", driverName)
                .append("mobile", mobile)
                .append("vehicleNumber", vehicleNumber)
                .append("address", address)
                .append("status", status)
                .append("createdOn", createdOn)
                .append("modifiedOn", modifiedOn)
                .append("deleteStatus", deleteStatus)
                .append("attendence", attendence)
                .append("route_id", route_id)
                .append("route", route)
                .toString();
    }

    public class BusSchedule {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("admin_id")
        @Expose
        public String adminId;
        @SerializedName("schoolbus_id")
        @Expose
        public String schoolbusId;
        @SerializedName("date")
        @Expose
        public String date;
        @SerializedName("in_time")
        @Expose
        public String inTime;
        @SerializedName("out_time")
        @Expose
        public String outTime;
        @SerializedName("in_out_status")
        @Expose
        public String inOutStatus;
        @SerializedName("created_on")
        @Expose
        public String createdOn;
        @SerializedName("modified_on")
        @Expose
        public String modifiedOn;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("id", id).append("adminId", adminId).append("schoolbusId", schoolbusId).append("date", date).append("inTime", inTime).append("outTime", outTime).append("inOutStatus", inOutStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).toString();
        }
    }
}
