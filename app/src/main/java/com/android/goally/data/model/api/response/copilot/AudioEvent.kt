package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class AudioEvent(
    @SerializedName("audioList")
    val audioList: List<Audio?>?,
    @SerializedName("event")
    val event: String?
)