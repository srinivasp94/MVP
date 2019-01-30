package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.iprismech.alertnikkiresidence.pojo.Contact;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Guestinvite {

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
    @SerializedName("week_dates")
    @Expose
    public List<WeekDate> weekDates = null;
    @SerializedName("contacts")
    @Expose
    public List<Contact> contacts = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("otpSentType", otpSentType).append("userType", userType).append("userId", userId).append("adminId", adminId).append("weekDates", weekDates).append("contacts", contacts).toString();
    }

    public static class WeekDate {

        @SerializedName("date")
        @Expose
        public String date;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("date", date).toString();
        }

    }

}
