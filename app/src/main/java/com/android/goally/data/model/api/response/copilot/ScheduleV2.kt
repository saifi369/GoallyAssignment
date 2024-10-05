package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class ScheduleV2(
    @SerializedName("dailyRepeatValues")
    val dailyRepeatValues: DailyRepeatValues?,
    @SerializedName("timeType")
    val timeType: String?,
    @SerializedName("type")
    val type: String?
)