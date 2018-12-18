package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class DigitalGatepassAlert {
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("list_id")
    @Expose
    public String listId;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("entry_in")
    @Expose
    public String entryIn;
    @SerializedName("entry_out")
    @Expose
    public String entryOut;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("member")
    @Expose
    public MemberDigital member;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userType", userType).append("userId", userId).append("listId", listId).append("date", date).append("entryIn", entryIn).append("entryOut", entryOut).append("image", image).append("description", description).append("member", member).toString();
    }

    public class MemberDigital {
        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("passcode")
        @Expose
        public String passcode;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("relation")
        @Expose
        public String relation;
        @SerializedName("blood_group")
        @Expose
        public String bloodGroup;
        @SerializedName("vehicle_type")
        @Expose
        public String vehicleType;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("qrcode")
        @Expose
        public String qrcode;
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
        @SerializedName("admin_id")
        @Expose
        public String adminId;
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
        @SerializedName("member_type")
        @Expose
        public String memberType;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("id", id).append("userId", userId).append("passcode", passcode).append("name", name).append("mobile", mobile).append("relation", relation).append("bloodGroup", bloodGroup).append("vehicleType", vehicleType).append("image", image).append("qrcode", qrcode).append("token", token).append("iosToken", iosToken).append("status", status).append("deleteStatus", deleteStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("adminId", adminId).append("city", city).append("society", society).append("building", building).append("flat", flat).append("memberType", memberType).toString();
        }

    }
}

