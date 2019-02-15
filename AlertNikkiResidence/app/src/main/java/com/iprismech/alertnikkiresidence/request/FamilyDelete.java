package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class FamilyDelete {
    @SerializedName("member_id")
    @Expose
    public String member_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("member_id", member_id)
                .toString();
    }
}
