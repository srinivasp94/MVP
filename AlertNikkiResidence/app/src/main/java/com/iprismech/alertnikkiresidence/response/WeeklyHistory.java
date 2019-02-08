package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class WeeklyHistory extends ExpandableGroup<Smenu> {


    public WeeklyHistory(String titleDate, List<Smenu> items) {
        super(titleDate, items);
    }

    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("smenu")
    @Expose
    public List<Smenu> smenu = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("date", date).append("smenu", smenu).toString();
    }
}


