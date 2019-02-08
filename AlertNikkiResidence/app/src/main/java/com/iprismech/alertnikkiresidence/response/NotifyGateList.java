package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotifyGateList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("service_type")
    @Expose
    public String serviceType;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("delivery_from")
    @Expose
    public String deliveryFrom;
    @SerializedName("service_from")
    @Expose
    public String serviceFrom;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicleNo;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("vaild_to")
    @Expose
    public String vaildTo;
    @SerializedName("from_date_time")
    @Expose
    public String fromDateTime;
    @SerializedName("to_date_time")
    @Expose
    public String toDateTime;
    @SerializedName("alert_status")
    @Expose
    public String alertStatus;
    @SerializedName("no_of_persons")
    @Expose
    public String noOfPersons;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("in_date")
    @Expose
    public String inDate;
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
    @SerializedName("cron_alert")
    @Expose
    public String cronAlert;
    @SerializedName("allow_security_id")
    @Expose
    public String allowSecurityId;
    @SerializedName("security_accept_status")
    @Expose
    public String securityAcceptStatus;
    @SerializedName("primary_call_status")
    @Expose
    public String primaryCallStatus;
    @SerializedName("secondary_call_status")
    @Expose
    public String secondaryCallStatus;
    @SerializedName("service")
    @Expose
    public String service;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userType", userType).append("userId", userId).append("adminId", adminId).append("serviceType", serviceType).append("image", image).append("deliveryFrom", deliveryFrom).append("serviceFrom", serviceFrom).append("vehicleNo", vehicleNo).append("name", name).append("mobile", mobile).append("fromDate", fromDate).append("vaildTo", vaildTo).append("fromDateTime", fromDateTime).append("toDateTime", toDateTime).append("alertStatus", alertStatus).append("noOfPersons", noOfPersons).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("inDate", inDate).append("inTime", inTime).append("outTime", outTime).append("allowDenyStatus", allowDenyStatus).append("resonForDeny", resonForDeny).append("inOutStatus", inOutStatus).append("cronAlert", cronAlert).append("allowSecurityId", allowSecurityId).append("securityAcceptStatus", securityAcceptStatus).append("primaryCallStatus", primaryCallStatus).append("secondaryCallStatus", secondaryCallStatus).append("service", service).toString();
    }
}
