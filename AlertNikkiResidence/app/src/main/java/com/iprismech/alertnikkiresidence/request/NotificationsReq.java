package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class NotificationsReq {
    @SerializedName("invite_guest")
    @Expose
    public String invite_guest;
    @SerializedName("my_staff")
    @Expose
    public String my_staff;
    @SerializedName("society_gate")
    @Expose
    public String society_gate;
    @SerializedName("school_bus")
    @Expose
    public String school_bus;
    @SerializedName("kids_gatepass")
    @Expose
    public String kids_gatepass;

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("invite_guest", invite_guest)
                .append("my_staff", my_staff)
                .append("society_gate", society_gate)
                .append("school_bus", school_bus)
                .append("kids_gatepass", kids_gatepass)
                .append("user_id", user_id)
                .toString();
    }
}
