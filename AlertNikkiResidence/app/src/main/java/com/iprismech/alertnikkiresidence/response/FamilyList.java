package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FamilyList {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("userId", userId).append("passcode", passcode).append("name", name).append("mobile", mobile).append("relation", relation).append("bloodGroup", bloodGroup).append("vehicleType", vehicleType).append("image", image).append("qrcode", qrcode).append("token", token).append("iosToken", iosToken).append("status", status).append("deleteStatus", deleteStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).toString();
    }
}
