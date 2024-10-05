package com.android.goally.data.model.api.response.copilot


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActivityX(
    @SerializedName("allowBack")
    val allowBack: Boolean?,
    @SerializedName("allowCancelActivity")
    val allowCancelActivity: Boolean?,
    @SerializedName("allowPauseActivity")
    val allowPauseActivity: Boolean?,
    @SerializedName("allowPush")
    val allowPush: Boolean?,
    @SerializedName("allowRestart")
    val allowRestart: Boolean?,
    @SerializedName("audioType")
    val audioType: String?,
    @SerializedName("audioUrl")
    val audioUrl: String?,
    @SerializedName("autoComplete")
    val autoComplete: Boolean?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("imgURL")
    val imgURL: String?,
    @SerializedName("libraryType")
    val libraryType: String?,
    @SerializedName("maxCompletionTime")
    val maxCompletionTime: Double?,
    @SerializedName("migrated")
    val migrated: Boolean?,
    @SerializedName("minCompletionTime")
    val minCompletionTime: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("numOfAudioRepeats")
    val numOfAudioRepeats: Int?,
    @SerializedName("openAIMp3Text")
    val openAIMp3Text: String?,
    @SerializedName("openAIMp3Url")
    val openAIMp3Url: String?,
    @SerializedName("ordering")
    val ordering: Int?,
    @SerializedName("parentActivityId")
    val parentActivityId: String?,
    @SerializedName("showTimer")
    val showTimer: Boolean?,
    @SerializedName("updatedAt")
    val updatedAt: String?
) : Parcelable