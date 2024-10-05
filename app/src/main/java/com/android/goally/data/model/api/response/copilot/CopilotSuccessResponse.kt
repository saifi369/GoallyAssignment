package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class CopilotSuccessResponse(
    @SerializedName("checklists")
    val checklists: List<Checklists>?,
    @SerializedName("routines")
    val routines: List<Routine>?
)