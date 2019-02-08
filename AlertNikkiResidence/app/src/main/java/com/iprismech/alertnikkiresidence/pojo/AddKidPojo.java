package com.iprismech.alertnikkiresidence.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class AddKidPojo {

    /**
     * user_type : 1
     * user_id : 1
     * admin_id : 2
     * kid_name : srinivas
     * purpose : [{"purpose":"Swimming","days":["1","2"],"in_time":"10:00","out_time":"11:40"},{"purpose":"dance","days":["1","2"],"in_time":"12:00","out_time":"13:40"},{"purpose":"Tutorials","days":["1","2"],"in_time":"15:00","out_time":"17:40"}]
     */

    @SerializedName("user_type")
    @Expose
    public String userType;
    @SerializedName("user_id")
    @Expose
    public String userId;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("kid_name")
    @Expose
    public String kidName;
    @SerializedName("purpose")
    @Expose
    public List<Purpose> purpose = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userType", userType).append("userId", userId).append("adminId", adminId).append("kidName", kidName).append("purpose", purpose).toString();
    }

}
