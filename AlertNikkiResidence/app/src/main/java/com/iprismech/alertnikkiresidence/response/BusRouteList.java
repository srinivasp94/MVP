package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BusRouteList {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("school_bus_id")
    @Expose
    public String schoolBusId;
    @SerializedName("route")
    @Expose
    public String route;
    @SerializedName("entry_time")
    @Expose
    public String entryTime;
    @SerializedName("exit_time")
    @Expose
    public String exitTime;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("modified_on")
    @Expose
    public String modifiedOn;
    @SerializedName("delete_status")
    @Expose
    public String deleteStatus;
    public boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("adminId", adminId).append("schoolBusId", schoolBusId).append("route", route).append("entryTime", entryTime).append("exitTime", exitTime).append("createdOn", createdOn).append("modifiedOn", modifiedOn).append("deleteStatus", deleteStatus).toString();
    }
}
