package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class GiveStaffRatingRequest {
    @SerializedName("user_type")
    @Expose
    public String user_type;

    @SerializedName("maid_id")
    @Expose
    public String maid_id;

    @SerializedName("user_id")
    @Expose
    public String user_id;

    @SerializedName("rating")
    @Expose
    public String rating;

    @SerializedName("description")
    @Expose
    public String description;


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("user_type", user_type)
                .append("maid_id", maid_id)
                .append("user_id", user_id)
                .append("rating", rating)
                .append("description", description)
                .toString();
    }
}
