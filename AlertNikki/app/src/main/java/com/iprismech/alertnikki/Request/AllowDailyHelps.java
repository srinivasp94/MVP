package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AllowDailyHelps {
    @SerializedName("maid_id")
    @Expose
    public String maid_id;
    @SerializedName("admin_id")
    @Expose
    public String admin_id;
    @SerializedName("security_id")
    @Expose
    public int security_id;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("security_id", security_id)
                .append("admin_id", admin_id).append("maid_id", maid_id).toString();
    }
}
