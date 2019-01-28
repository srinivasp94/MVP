package com.iprismech.alertnikkiresidence.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class SignupReq {
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("mobile")
    @Expose
    public String mobile;
    @SerializedName("email_id")
    @Expose
    public String emailId;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("otp_confirmed")
    @Expose
    public String otpConfirmed;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name).append("mobile", mobile).append("emailId", emailId).append("password", password).append("otpConfirmed", otpConfirmed).toString();
    }

}
