package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class ScheduleV2X(
    @SerializedName("dailyRepeatValues")
    val dailyRepeatValues: DailyRepeatValues?,
    @SerializedName("timeType")
    val timeType: String?,
    @SerializedName("timeValue")
    val timeValue: String?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("yearlyRepeatDateValue")
    val yearlyRepeatDateValue: String?
)