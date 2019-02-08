package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AddStaffMaidRequest {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("flat_id")
    @Expose
    public String flatId;
    @SerializedName("maid_id")
    @Expose
    public String maidId;
    @SerializedName("available_times")
    @Expose
    public List<String> availableTimes = null;
    @SerializedName("working_type")
    @Expose
    public String workingType;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("to_date")
    @Expose
    public String toDate;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("userType", userType).append("userId", userId).append("flatId", flatId).append("maidId", maidId).append("availableTimes", availableTimes).append("workingType", workingType).append("fromDate", fromDate).append("toDate", toDate).toString();
    }
}
