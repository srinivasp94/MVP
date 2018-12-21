package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Passcodes_Info {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public PasscodesResponse response;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }

    public class PasscodesResponse {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("admin_id")
        @Expose
        public String adminId;
        @SerializedName("device_id")
        @Expose
        public String deviceId;
        @SerializedName("shift_id")
        @Expose
        public String shiftId;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("passcode")
        @Expose
        public String passcode;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("experience")
        @Expose
        public String experience;
        @SerializedName("age")
        @Expose
        public String age;
        @SerializedName("address")
        @Expose
        public String address;
        @SerializedName("token")
        @Expose
        public String token;
        @SerializedName("ios_token")
        @Expose
        public String iosToken;
        @SerializedName("status")
        @Expose
        public String status;
        @SerializedName("created_on")
        @Expose
        public String createdOn;
        @SerializedName("modified_on")
        @Expose
        public String modifiedOn;
        @SerializedName("delete_status")
        @Expose
        public String deleteStatus;
        @SerializedName("IMI_Number")
        @Expose
        public String iMINumber;
        @SerializedName("title")
        @Expose
        public String title;
        @SerializedName("member_type")
        @Expose
        public String memberType;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("id", id).append("adminId", adminId).append("deviceId", deviceId).append("shiftId", shiftId).append("image", image).append("passcode", passcode).append("name", name).append("mobile", mobile).append("password", password).append("experience", experience).append("age", age).append("address", address).append("token", token).append("iosToken", iosToken).append("status", status).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("deleteStatus", deleteStatus).append("iMINumber", iMINumber).append("title", title).append("memberType", memberType).toString();
        }
    }
}
