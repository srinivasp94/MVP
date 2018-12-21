package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class DailyHelpsList {
    @SerializedName("maid_id")
    @Expose
    public String maidId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("designation")
    @Expose
    public String designation;
    @SerializedName("passcode")
    @Expose
    public String passcode;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("date_of_joining")
    @Expose
    public String dateOfJoining;
    @SerializedName("timings")
    @Expose
    public String timings;
    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("service")
    @Expose
    public String service;
    @SerializedName("society")
    @Expose
    public String society;
    @SerializedName("flats")
    @Expose
    public String flats;
    @SerializedName("attendence")
    @Expose
    public Attendence attendence = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("maidId", maidId).append("adminId", adminId).append("image", image).append("name", name).append("designation", designation).append("passcode", passcode).append("mobile", mobile).append("dateOfJoining", dateOfJoining).append("timings", timings).append("address", address).append("service", service).append("society", society).append("flats", flats).append("attendence", attendence).toString();
    }

    public class Attendence {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("admin_id")
        @Expose
        public String adminId;
        @SerializedName("maid_id")
        @Expose
        public String maidId;
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
        @SerializedName("allowed_security_id")
        @Expose
        public String allowedSecurityId;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("id", id).append("adminId", adminId).append("maidId", maidId).append("date", date).append("inTime", inTime).append("outTime", outTime).append("inOutStatus", inOutStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("allowedSecurityId", allowedSecurityId).toString();
        }
    }
}
