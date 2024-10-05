package com.android.goally.data.model.api.response.copilot


import com.google.gson.annotations.SerializedName

data class Routine(
    @SerializedName("activities")
    val activities: List<ActivityX>?,
    @SerializedName("allowClientToCancel")
    val allowClientToCancel: Boolean?,
    @SerializedName("allowEarlyStart")
    val allowEarlyStart: Boolean?,
    @SerializedName("allowRecap")
    val allowRecap: Boolean?,
    @SerializedName("allowSnooze")
    val allowSnooze: Boolean?,
    @SerializedName("allowToOverride")
    val allowToOverride: Boolean?,
    @SerializedName("allowUpdateNightLightAndNoiseMachine")
    val allowUpdateNightLightAndNoiseMachine: Boolean?,
    @SerializedName("appRewards")
    val appRewards: List<Any>?,
    @SerializedName("audioEvents")
    val audioEvents: List<AudioEventX>?,
    @SerializedName("category")
    val category: String?,
    @SerializedName("clientId")
    val clientId: String?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("createdBy")
    val createdBy: String?,
    @SerializedName("ctaOrdering")
    val ctaOrdering: Int?,
    @SerializedName("devices")
    val devices: List<String>?,
    @SerializedName("earlyStartMinutes")
    val earlyStartMinutes: Int?,
    @SerializedName("enableChillZoneWatch")
    val enableChillZoneWatch: Boolean?,
    @SerializedName("enableEmotionalFeedback")
    val enableEmotionalFeedback: Boolean?,
    @SerializedName("enableSpeedMonitoring")
    val enableSpeedMonitoring: Boolean?,
    @SerializedName("enableVacationMode")
    val enableVacationMode: Boolean?,
    @SerializedName("enableWeatherWatch")
    val enableWeatherWatch: Boolean?,
    @SerializedName("entityType")
    val entityType: String?,
    @SerializedName("folder")
    val folder: String?,
    @SerializedName("folderId")
    val folderId: String?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("imgURL")
    val imgURL: String?,
    @SerializedName("isCreatedByDevice")
    val isCreatedByDevice: Boolean?,
    @SerializedName("isMarkedHot")
    val isMarkedHot: Boolean?,
    @SerializedName("isScheduledByV2")
    val isScheduledByV2: Boolean?,
    @SerializedName("isVisibleToAudience")
    val isVisibleToAudience: Boolean?,
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
    @SerializedName("migrated")
    val migrated: Boolean?,
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
    @SerializedName("openAIMp3Text")
    val openAIMp3Text: String?,
    @SerializedName("openAIMp3Url")
    val openAIMp3Url: String?,
    @SerializedName("ordering")
    val ordering: Int?,
    @SerializedName("parentRoutineId")
    val parentRoutineId: String?,
    @SerializedName("requiredRewardApproval")
    val requiredRewardApproval: Boolean?,
    @SerializedName("rewardType")
    val rewardType: String?,
    @SerializedName("routineFolder")
    val routineFolder: String?,
    @SerializedName("routineNotifications")
    val routineNotifications: List<RoutineNotification>?,
    @SerializedName("routineNotificationsV2")
    val routineNotificationsV2: List<RoutineNotificationsV2>?,
    @SerializedName("schedule")
    val schedule: ScheduleX?,
    @SerializedName("scheduleV2")
    val scheduleV2: ScheduleV2X?,
    @SerializedName("showOnLearnerApp")
    val showOnLearnerApp: Boolean?,
    @SerializedName("showTimer")
    val showTimer: Boolean?,
    @SerializedName("tags")
    val tags: List<String>?,
    @SerializedName("templateDisabledClientIds")
    val templateDisabledClientIds: List<Any>?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("updatedAt")
    val updatedAt: String?,
    @SerializedName("__v")
    val v: Int?
)