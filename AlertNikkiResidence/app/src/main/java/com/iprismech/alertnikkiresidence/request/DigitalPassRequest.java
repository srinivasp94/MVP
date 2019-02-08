package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class DigitalPassRequest {


    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("maid_id")
    @Expose
    public String maidId;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("entry_in")
    @Expose
    public String entryIn;
    @SerializedName("entry_out")
    @Expose
    public String entryOut;
    @SerializedName("Platform")
    @Expose
    public String platform;
    @SerializedName("images")
    @Expose
   // public JSONArray images = null;
    public List<ImageItems> images = null;


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userType", userType).
                append("userId", userId).append("adminId", adminId)
                .append("maidId", maidId).append("description", description)
                .append("entryIn", entryIn).append("entryOut", entryOut)
                .append("platform", platform).append("images", images).toString();
    }
    public static class ImageItems {

        @SerializedName("image")
        @Expose
        public String image;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("image", image).toString();
        }

    }
}



