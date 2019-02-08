package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class EditKidRequest {
    @SerializedName("user_kid_id")
    @Expose
    public String user_kid_id;
    @SerializedName("purpose")
    @Expose
    public String purpose;
    @SerializedName("in_time")
    @Expose
    public String in_time;
    @SerializedName("Out_time")
    @Expose
    public String Out_time;
    @SerializedName("kid_name")
    @Expose
    public String kid_name;

    @SerializedName("days")
    @Expose
    public List<String> days = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_kid_id", user_kid_id)
                .append("purpose", purpose)
                .append("in_time", in_time)
                .append("Out_time", Out_time)
                .append("kid_name", kid_name)
                .append("days", days)
                .toString();
    }
}
