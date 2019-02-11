package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class DigitalGatepassAlert {
    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("list_id")
    @Expose
    public String listId;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("entry_in")
    @Expose
    public String entryIn;
    @SerializedName("entry_out")
    @Expose
    public String entryOut;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("member")
    @Expose
    public MemberDigital member;
    @SerializedName("images")
    @Expose
    public List<DigitalGateImagesList> images = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userType", userType)
                .append("userId", userId)
                .append("listId", listId)
                .append("date", date)
                .append("entryIn", entryIn)
                .append("entryOut", entryOut)
                .append("image", image)
                .append("description", description)
                .append("member", member)
                .append("images", images)
                .toString();
    }
}

