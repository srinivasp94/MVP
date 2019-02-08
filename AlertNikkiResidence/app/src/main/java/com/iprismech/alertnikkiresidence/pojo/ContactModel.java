package com.iprismech.alertnikkiresidence.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class ContactModel implements Parcelable {
    private String contactId, contactName, contactNumber, vehiclenumber;
    public boolean contactisChecked = false;

    public ContactModel() {
    }

    public ContactModel(String contactName, String contactNumber, String vehiclenumber) {
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.vehiclenumber = vehiclenumber;
    }

    public ContactModel(String contactId, String contactName, String contactNumber, boolean contactisChecked) {
        this.contactId = contactId;
        this.contactName = contactName;
        this.contactNumber = contactNumber;
        this.contactisChecked = contactisChecked;
    }


    public boolean isContactChecked() {
        return contactisChecked;
    }

    public void setContactChecked(boolean contactisChecked) {
        this.contactisChecked = contactisChecked;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    protected ContactModel(Parcel in) {
        contactId = in.readString();
        contactName = in.readString();
        contactNumber = in.readString();
        vehiclenumber = in.readString();
        contactisChecked = in.readByte() != 0;
    }

    public static final Creator<ContactModel> CREATOR = new Creator<ContactModel>() {
        @Override
        public ContactModel createFromParcel(Parcel in) {
            return new ContactModel(in);
        }

        @Override
        public ContactModel[] newArray(int size) {
            return new ContactModel[size];
        }
    };


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactId);
        dest.writeString(contactName);
        dest.writeString(contactNumber);
        dest.writeString(vehiclenumber);
        dest.writeByte((byte) (contactisChecked ? 1 : 0));
    }

}
