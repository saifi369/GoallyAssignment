package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class RoutineNotificationsV2(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("minutesBefore")
    val minutesBefore: Int?,
    @SerializedName("notificationType")
    val notificationType: String?,
    @SerializedName("scheduledTime")
    val scheduledTime: String?
)