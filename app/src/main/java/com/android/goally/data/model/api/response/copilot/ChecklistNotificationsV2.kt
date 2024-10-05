package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class ChecklistNotificationsV2(
    @SerializedName("_id")
    val id: String?,
    @SerializedName("minutesBefore")
    val minutesBefore: Int?,
    @SerializedName("notificationType")
    val notificationType: String?
)