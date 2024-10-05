package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class LastSchedule(
    @SerializedName("Fri")
    val fri: Any?,
    @SerializedName("Mon")
    val mon: Any?,
    @SerializedName("Sat")
    val sat: Any?,
    @SerializedName("Sun")
    val sun: Any?,
    @SerializedName("Thu")
    val thu: Any?,
    @SerializedName("Tue")
    val tue: Any?,
    @SerializedName("Wed")
    val wed: Any?
)