package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class TypleList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("slug")
    @Expose
    public String slug;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("delete_status")
    @Expose
    public String deleteStatus;
    public boolean isSelect = false;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("adminId", adminId)
                .append("title", title)
                .append("slug", slug)
                .append("createdOn", createdOn)
                .append("modifiedOn", modifiedOn)
                .append("deleteStatus", deleteStatus)
                .append("isSelect", isSelect)
                .toString();
    }
}
