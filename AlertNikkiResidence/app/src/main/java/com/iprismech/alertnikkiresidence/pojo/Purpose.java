package com.iprismech.alertnikkiresidence.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class Purpose {
    @SerializedName("purpose")
    @Expose
    public String purpose;
    @SerializedName("days")
    @Expose
    public List<String> days = null;
    @SerializedName("in_time")
    @Expose
    public String inTime;
    @SerializedName("out_time")
    @Expose
    public String outTime;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("purpose", purpose).append("days", days).append("inTime", inTime).append("outTime", outTime).toString();
    }
}
