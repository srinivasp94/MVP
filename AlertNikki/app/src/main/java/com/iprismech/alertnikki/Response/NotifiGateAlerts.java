package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.lang.reflect.Member;

public class NotifiGateAlerts {

    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("notify_gate_id")
    @Expose
    public String notifyGateId;
    @SerializedName("person_name")
    @Expose
    public String personName;
    @SerializedName("person_mobile")
    @Expose
    public String personMobile;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("from_date_time")
    @Expose
    public String fromDateTime;
    @SerializedName("to_date_time")
    @Expose
    public String toDateTime;
    @SerializedName("service")
    @Expose
    public String service;
    @SerializedName("member")
    @Expose
    public MemberDigital member;


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userType", userType).append("userId", userId).append("notifyGateId", notifyGateId).append("personName", personName).append("personMobile", personMobile).append("fromDate", fromDate).append("fromDateTime", fromDateTime).append("toDateTime", toDateTime).append("service", service).append("member", member).toString();
    }
}
