package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SocietyList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("city_id")
    @Expose
    public String cityId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("google_map_link")
    @Expose
    public String googleMapLink;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("delete_status")
    @Expose
    public String deleteStatus;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("cityId", cityId).append("adminId", adminId).append("title", title).append("slug", slug).append("googleMapLink", googleMapLink).append("status", status).append("deleteStatus", deleteStatus).append("createdOn", createdOn).append("modifiedOn", modifiedOn).toString();
    }
}
