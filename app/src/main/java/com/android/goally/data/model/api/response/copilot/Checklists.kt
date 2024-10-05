package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class Checklists(
    @SerializedName("activities")
    val activities: List<Activity?>?,
    @SerializedName("allowCancel")
    val allowCancel: Boolean?,
    @SerializedName("allowComplete")
    val allowComplete: Boolean?,
    @SerializedName("allowPause")
    val allowPause: Boolean?,
    @SerializedName("allowRestart")
    val allowRestart: Boolean?,
    @SerializedName("allowSnooze")
    val allowSnooze: Boolean?,
    @SerializedName("allowToOverride")
    val allowToOverride: Boolean?,
    @SerializedName("appRewards")
    val appRewards: List<Any?>?,
    @SerializedName("audioEvents")
    val audioEvents: List<AudioEvent?>?,
    @SerializedName("checklistNotifications")
    val checklistNotifications: List<ChecklistNotifications?>?,
    @SerializedName("checklistNotificationsV2")
    val checklistNotificationsV2: List<ChecklistNotificationsV2?>?,
    @SerializedName("clientId")
    val clientId: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("ctaOrdering")
    val ctaOrdering: Int?,
    @SerializedName("devices")
    val devices: List<String?>?,
    @SerializedName("duration")
    val duration: Int?,
    @SerializedName("earlyStartMinutes")
    val earlyStartMinutes: Int?,
    @SerializedName("enableAudioAid")
    val enableAudioAid: Boolean?,
    @SerializedName("enableEmotionalFeedback")
    val enableEmotionalFeedback: Boolean?,
    @SerializedName("enableVacationMode")
    val enableVacationMode: Boolean?,
    @SerializedName("entityType")
    val entityType: String?,
    @SerializedName("folder")
    val folder: String?,
    @SerializedName("folderId")
    val folderId: String?,
    @SerializedName("hideActAfterCom")
    val hideActAfterCom: Boolean?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("isScheduledByV2")
    val isScheduledByV2: Boolean?,
    @SerializedName("label")
    val label: String?,
    @SerializedName("lastSchedule")
    val lastSchedule: LastSchedule?,
    @SerializedName("libraryType")
    val libraryType: String?,
    @SerializedName("limitEarlyStart")
    val limitEarlyStart: Boolean?,
    @SerializedName("limitRunPerDay")
    val limitRunPerDay: Boolean?,
    @SerializedName("limitRunPerHour")
    val limitRunPerHour: Boolean?,
    @SerializedName("minDuration")
    val minDuration: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("narration")
    val narration: Boolean?,
    @SerializedName("notifsV2SoundName")
    val notifsV2SoundName: String?,
    @SerializedName("notifsV2SoundUrl")
    val notifsV2SoundUrl: String?,
    @SerializedName("numberOfPointsLate")
    val numberOfPointsLate: Int?,
    @SerializedName("numberOfPointsOnTime")
    val numberOfPointsOnTime: Int?,
    @SerializedName("numberOfPuzzlesLate")
    val numberOfPuzzlesLate: Int?,
    @SerializedName("numberOfPuzzlesOnTime")
    val numberOfPuzzlesOnTime: Int?,
    @SerializedName("numberOfRunPerDay")
    val numberOfRunPerDay: Int?,
    @SerializedName("numberOfRunPerHour")
    val numberOfRunPerHour: Int?,
    @SerializedName("numberOfSnoozeAllowed")
    val numberOfSnoozeAllowed: Int?,
    @SerializedName("ordering")
    val ordering: Int?,
    @SerializedName("requiredRewardApproval")
    val requiredRewardApproval: Boolean?,
    @SerializedName("rewardType")
    val rewardType: String?,
    @SerializedName("schedule")
    val schedule: Schedule?,
    @SerializedName("scheduleV2")
    val scheduleV2: ScheduleV2?,
    @SerializedName("showOnLearnerApp")
    val showOnLearnerApp: Boolean?,
    @SerializedName("showTimer")
    val showTimer: Boolean?,
    @SerializedName("tags")
    val tags: List<String?>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("__v")
    val v: Int?,
    @SerializedName("visualAidUrl")
    val visualAidUrl: String?
)