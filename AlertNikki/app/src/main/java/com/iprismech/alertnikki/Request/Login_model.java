package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Login_model {
    @SerializedName("passcode")
    @Expose
    public String passcode;

    @SerializedName("selected_image")
    @Expose
    public String selected_image;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("passcode", passcode).append("selected_image", selected_image).toString();
    }
}
