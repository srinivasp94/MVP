package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class RegisterFamilyMember {
    @SerializedName("user_id")
    @Expose
    public String userId;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userId", userId).append("name", name).append("mobile", mobile).append("relation", relation).append("bloodGroup", bloodGroup).toString();
    }
}
