package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class Audio(
    @SerializedName("isSelected")
    val isSelected: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("ordering")
    val ordering: Int?,
    @SerializedName("paUrl")
    val paUrl: String?,
    @SerializedName("url")
    val url: String?
)