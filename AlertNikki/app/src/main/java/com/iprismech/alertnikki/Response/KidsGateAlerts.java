package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class KidsGateAlerts {
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("kid_name")
    @Expose
    public String kidName;
    @SerializedName("purpose")
    @Expose
    public String purpose;
    @SerializedName("kid_going_with_whom")
    @Expose
    public String kidGoingWithWhom;
    @SerializedName("from_date")
    @Expose
    public String fromDate;
    @SerializedName("in_time")
    @Expose
    public String inTime;
    @SerializedName("out_time")
    @Expose
    public String outTime;
    @SerializedName("member")
    @Expose
    public MemberDigital member;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userType", userType).append("from_date", fromDate).append("userId", userId).append("kidName", kidName).append("purpose", purpose).append("kidGoingWithWhom", kidGoingWithWhom).append("inTime", inTime).append("outTime", outTime).append("member", member).toString();
    }

}
