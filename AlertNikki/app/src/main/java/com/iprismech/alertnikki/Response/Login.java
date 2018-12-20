package com.iprismech.alertnikki.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Login {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("response")
    @Expose
    public ResponseLogin response;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("response", response).toString();
    }

    public class ResponseLogin {

        @SerializedName("admin_id")
        @Expose
        public String adminId;
        @SerializedName("security_id")
        @Expose
        public int securityId;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("login_date")
        @Expose
        public String loginDate;
        @SerializedName("login_time")
        @Expose
        public String loginTime;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("society")
        @Expose
        public String society;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("adminId", adminId).append("securityId", securityId).append("image", image).append("loginData", loginDate).append("loginTime", loginTime).append("city", city).append("society", society).toString();
        }
    }
}
