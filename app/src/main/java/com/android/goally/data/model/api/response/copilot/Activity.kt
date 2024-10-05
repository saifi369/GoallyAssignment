package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class Activity(
    @SerializedName("audioType")
    val audioType: String?,
    @SerializedName("audioUrl")
    val audioUrl: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ordering")
    val ordering: Int?
)