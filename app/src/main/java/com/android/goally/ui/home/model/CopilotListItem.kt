package com.android.goally.ui.home.model

import android.os.Parcelable
import com.android.goally.data.model.api.response.copilot.ActivityX
import kotlinx.parcelize.Parcelize

@Parcelize
data class CopilotListItem(
    val name: String,
    val imageUrl: String,
    val schedule: String,
    val displaySchedule: String,
    val folderName: String,
    val activities: List<ActivityX>
) : Parcelable