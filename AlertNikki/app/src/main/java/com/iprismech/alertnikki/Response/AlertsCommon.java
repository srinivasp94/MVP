package com.iprismech.alertnikki.Response;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AlertsCommon {
    public String date;
    public String service;
    public String Description;
    public String type_alert;
    public String inTime;
    public String outTime;
    public String profilePic;

    public String id;
    public String passcode;
    public String name;
    public String phone;
    public String society;
    public String flat;
    public String residence_type;
    public String Building;
    public String memberType;

    public AlertsCommon() {
    }

    public AlertsCommon(String date, String service, String description, String type_alert, String id, String passcode, String name, String phone, String society, String flat, String residence_type, String building, String memberType) {
        this.date = date;
        this.service = service;
        Description = description;
        this.type_alert = type_alert;
        this.id = id;
        this.passcode = passcode;
        this.name = name;
        this.phone = phone;
        this.society = society;
        this.flat = flat;
        this.residence_type = residence_type;
        Building = building;
        this.memberType = memberType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("date", date)
                .append("service", service)
                .append("Description", Description)
                .append("type_alert", type_alert)
                .append("inTime", inTime)
                .append("outTime", outTime)
                .append("profilePic", profilePic)
                .append("id", id)
                .append("passcode", passcode)
                .append("name", name)
                .append("phone", phone)
                .append("society", society)
                .append("flat", flat)
                .append("residence_type", residence_type)
                .append("Building", Building)
                .append("memberType", memberType)
                .toString();
    }
}
