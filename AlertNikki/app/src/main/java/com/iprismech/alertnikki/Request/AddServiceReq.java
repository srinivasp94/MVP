package com.iprismech.alertnikki.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AddServiceReq {

    @SerializedName("admin_id")
    @Expose
    public String admin_id;

    @SerializedName("service_id")
    @Expose
    public String service_id;
    @SerializedName("security_id")
    @Expose
    public String security_id;

    @SerializedName("name")
    @Expose
    public String name;

    @SerializedName("address")
    @Expose
    public String address;

    @SerializedName("mobile")
    @Expose
    public String mobile;

    @SerializedName("id_proof_type")
    @Expose
    public String id_proof_type;

    @SerializedName("id_proof_number")
    @Expose
    public String id_proof_number;

    @SerializedName("photo")
    @Expose
    public String photo;


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("admin_id", admin_id).append("service_id", service_id).append("security_id", security_id)
                .append("name", name).append("address", address)
                .append("mobile", mobile).append("id_proof_type", id_proof_type)
                .append("id_proof_number", id_proof_number).append("photo", photo).toString();
    }
}
