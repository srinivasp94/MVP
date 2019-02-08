package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class ProfileRes {


    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public ProfileDetails response;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }

    public class ProfileDetails {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("admin_id")
        @Expose
        public String adminId;
        @SerializedName("passcode")
        @Expose
        public String passcode;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("email_id")
        @Expose
        public String emailId;
        @SerializedName("blood_group")
        @Expose
        public String bloodGroup;
        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("otp")
        @Expose
        public String otp;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("qrcode")
        @Expose
        public String qrcode;
        @SerializedName("city_id")
        @Expose
        public String cityId;
        @SerializedName("society_id")
        @Expose
        public String societyId;
        @SerializedName("building_id")
        @Expose
        public String buildingId;
        @SerializedName("flat_id")
        @Expose
        public String flatId;
        @SerializedName("flats")
        @Expose
        public Object flats;
        @SerializedName("residence_type_id")
        @Expose
        public String residenceTypeId;
        @SerializedName("token")
        @Expose
        public String token;
        @SerializedName("ios_token")
        @Expose
        public String iosToken;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("delete_status")
        @Expose
        public String deleteStatus;
        @SerializedName("created_on")
        @Expose
        public String createdOn;
        @SerializedName("modified_on")
        @Expose
        public String modifiedOn;
        @SerializedName("otp_submit_status")
        @Expose
        public String otpSubmitStatus;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("society")
        @Expose
        public String society;
        @SerializedName("building")
        @Expose
        public String building;
        @SerializedName("flat")
        @Expose
        public String flat;
        @SerializedName("residence_type")
        @Expose
        public String residenceType;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("id", id).append("adminId", adminId).append("passcode", passcode).append("name", name).append("mobile", mobile).append("emailId", emailId).append("bloodGroup", bloodGroup).append("password", password).append("otp", otp).append("image", image).append("qrcode", qrcode).append("cityId", cityId).append("societyId", societyId).append("buildingId", buildingId).append("flatId", flatId).append("flats", flats).append("residenceTypeId", residenceTypeId).append("token", token).append("iosToken", iosToken).append("status", status).append("deleteStatus", deleteStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("otpSubmitStatus", otpSubmitStatus).append("city", city).append("society", society).append("building", building).append("flat", flat).append("residenceType", residenceType).toString();
        }
    }
}
