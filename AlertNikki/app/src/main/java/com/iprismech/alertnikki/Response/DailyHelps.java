package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class DailyHelps {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public List<DailyHelpsList> response = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }

}
