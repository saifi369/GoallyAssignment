package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class ScheduleX(
    @SerializedName("Fri")
    val fri: String?,
    @SerializedName("Mon")
    val mon: String?,
    @SerializedName("Sat")
    val sat: String?,
    @SerializedName("Sun")
    val sun: String?,
    @SerializedName("Thu")
    val thu: String?,
    @SerializedName("Tue")
    val tue: String?,
    @SerializedName("Wed")
    val wed: String?
)