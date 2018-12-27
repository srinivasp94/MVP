package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class MoveIn {
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("security_id")
    @Expose
    public String security_id;

    @SerializedName("building_id")
    @Expose
    public String building_id;
    @SerializedName("flat_id")
    @Expose
    public String flat_num;
    @SerializedName("vehicle_no")
    @Expose
    public String vehicle_num;
    @SerializedName("tenant_name")
    @Expose
    public String tenant_name;
    @SerializedName("mobile_no")
    @Expose
    public String mobile_num;
    @SerializedName("comments")
    @Expose
    public String comments;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("adminId", adminId).append("security_id", security_id).append("building_id", building_id).append("flat_id", flat_num)
                .append("vehicle_num", vehicle_num).append("tenant_name", tenant_name)
                .append("mobile_num", mobile_num).append("comments", comments).toString();
    }
}
