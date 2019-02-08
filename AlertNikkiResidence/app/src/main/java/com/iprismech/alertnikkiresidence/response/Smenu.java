package com.iprismech.alertnikkiresidence.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Smenu implements Parcelable {
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("admin_id")
    @Expose
    public String adminId;
    @SerializedName("schoolbus_id")
    @Expose
    public String schoolbusId;
    @SerializedName("schoolbus_name")
    @Expose
    public String schoolbusName;
    @SerializedName("schoolbus_address")
    @Expose
    public String schoolbusAddress;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("in_time")
    @Expose
    public String inTime;
    @SerializedName("out_time")
    @Expose
    public String outTime;

    protected Smenu(Parcel in) {
        id = in.readString();
        adminId = in.readString();
        schoolbusId = in.readString();
        schoolbusName = in.readString();
        schoolbusAddress = in.readString();
        date = in.readString();
        inTime = in.readString();
        outTime = in.readString();
    }

    public static final Creator<Smenu> CREATOR = new Creator<Smenu>() {
        @Override
        public Smenu createFromParcel(Parcel in) {
            return new Smenu(in);
        }

        @Override
        public Smenu[] newArray(int size) {
            return new Smenu[size];
        }
    };

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("adminId", adminId).append("schoolbusId", schoolbusId).append("schoolbusName", schoolbusName).append("schoolbusAddress", schoolbusAddress).append("date", date).append("inTime", inTime).append("outTime", outTime).toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(adminId);
        dest.writeString(schoolbusId);
        dest.writeString(schoolbusName);
        dest.writeString(schoolbusAddress);
        dest.writeString(date);
        dest.writeString(inTime);
        dest.writeString(outTime);
    }
}
