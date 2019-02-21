package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FamilydeviceToken {
    @SerializedName("family_member_id")
    @Expose
    public String familyMemberId;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("ios_token")
    @Expose
    public String iosToken;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("familyMemberId", familyMemberId).append("token", token).append("iosToken", iosToken).toString();
    }

}
