package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GuestsList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("otp_sent_type")
    @Expose
    public String otpSentType;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("otp")
    @Expose
    public String otp;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("no_of_persons")
    @Expose
    public String noOfPersons;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;
    @SerializedName("from_date_time")
    @Expose
    public String fromDateTime;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("in_time")
    @Expose
    public String inTime;
    @SerializedName("out_time")
    @Expose
    public String outTime;
    @SerializedName("allow_deny_status")
    @Expose
    public String allowDenyStatus;
    @SerializedName("reson_for_deny")
    @Expose
    public String resonForDeny;
    @SerializedName("in_out_status")
    @Expose
    public String inOutStatus;
    @SerializedName("allow_security_id")
    @Expose
    public String allowSecurityId;
    @SerializedName("security_accept_status")
    @Expose
    public String securityAcceptStatus;
    @SerializedName("day")
    @Expose
    public String day;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("otpSentType", otpSentType).append("userType", userType).append("userId", userId).append("adminId", adminId).append("otp", otp).append("image", image).append("noOfPersons", noOfPersons).append("name", name).append("mobile", mobile).append("vehicleNo", vehicleNo).append("fromDateTime", fromDateTime).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("inTime", inTime).append("outTime", outTime).append("allowDenyStatus", allowDenyStatus).append("resonForDeny", resonForDeny).append("inOutStatus", inOutStatus).append("allowSecurityId", allowSecurityId).append("securityAcceptStatus", securityAcceptStatus).append("day", day).toString();
    }

}
