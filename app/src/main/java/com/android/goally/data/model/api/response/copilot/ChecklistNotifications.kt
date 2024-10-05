package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class ChecklistNotifications(
    @SerializedName("audioUrl")
    val audioUrl: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("isActive")
    val isActive: Boolean?,
    @SerializedName("isReadText")
    val isReadText: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("timeBefore")
    val timeBefore: Int?
)