package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RegisterConfirm {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("email_id")
    @Expose
    public String emailId;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("blood_group")
    @Expose
    public String bloodGroup;
    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("society_id")
    @Expose
    public String societyId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("building_id")
    @Expose
    public String buildingId;
    @SerializedName("flat_id")
    @Expose
    public String flatId;
    @SerializedName("residence_type_id")
    @Expose
    public String residenceTypeId;
    @SerializedName("otp_confirmed")
    @Expose
    public String otpConfirmed;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("mobile", mobile).append("emailId", emailId).append("password", password).append("bloodGroup", bloodGroup).append("cityId", cityId).append("societyId", societyId).append("adminId", adminId).append("buildingId", buildingId).append("flatId", flatId).append("residenceTypeId", residenceTypeId).append("otpConfirmed", otpConfirmed).toString();
    }

}
