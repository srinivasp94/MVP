package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class StaffResponse {
    @SerializedName("staff_id")
    @Expose
    public String staffId;
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
    @SerializedName("attendence")
    @Expose
    public List<Object> attendence = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("staffId", staffId).append("adminId", adminId).append("image", image).append("name", name).append("designation", designation).append("passcode", passcode).append("mobile", mobile).append("dateOfJoining", dateOfJoining).append("timings", timings).append("address", address).append("attendence", attendence).toString();
    }

}
