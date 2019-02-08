package com.iprismech.alertnikkiresidence.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class HistoryResponse {
    @SerializedName("weekly_history")
    @Expose
    public List<WeeklyHistory> weeklyHistory = null;
    @SerializedName("monthly_history")
    @Expose
    public List<MonthlyHistory> monthlyHistory = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("weeklyHistory", weeklyHistory).append("monthlyHistory", monthlyHistory).toString();
    }
}
