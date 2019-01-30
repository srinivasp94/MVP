package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SocietyReq {
    @SerializedName("city_id")
    @Expose
    public String cityId;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("cityId", cityId).toString();
    }
}
