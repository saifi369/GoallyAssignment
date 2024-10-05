package com.android.goally.ui.utils

import com.android.goally.data.model.api.response.copilot.ScheduleV2X
import com.android.goally.ui.home.model.CopilotFilterItem
import com.android.goally.ui.home.model.CopilotListItem
import com.android.goally.ui.home.model.FilterItem
import com.android.goally.ui.home.model.FilterType
import javax.inject.Inject

class ScheduleMapper @Inject constructor() {

    fun mapApiResponseToUiValues(schedule: ScheduleV2X?): String {
        val repeatValues = schedule?.dailyRepeatValues
            ?: return when (schedule?.type) {
                ScheduleConstants.API_REPEATING_YEARLY -> ScheduleConstants.DISPLAY_YEARLY
                else -> ScheduleConstants.DISPLAY_NOT_SCHEDULED
            }

        val weekdays = listOf(
            "Mon" to repeatValues.mon,
            "Tue" to repeatValues.tue,
            "Wed" to repeatValues.wed,
            "Thu" to repeatValues.thu,
            "Fri" to repeatValues.fri
        )

        val weekends = listOf(
            "Sat" to repeatValues.sat,
            "Sun" to repeatValues.sun
        )

        val hasWeekdays = weekdays.all { !it.second.isNullOrEmpty() }
        val hasWeekends = weekends.all { !it.second.isNullOrEmpty() }

        return when {
            hasWeekdays && hasWeekends -> ScheduleConstants.DISPLAY_EVERYDAY
            hasWeekdays && !hasWeekends -> ScheduleConstants.DISPLAY_WEEKDAYS
            !hasWeekdays && hasWeekends -> ScheduleConstants.DISPLAY_WEEKENDS
            else -> {
                val scheduledDays = (weekdays + weekends)
                    .filter { !it.second.isNullOrEmpty() }
                    .joinToString(", ") { it.first }
                scheduledDays.ifEmpty { ScheduleConstants.DISPLAY_NOT_SCHEDULED }
            }
        }
    }

    fun mapScheduleToFilter(schedule: String): String {
        return when (schedule) {
            ScheduleConstants.API_REPEATING_YEARLY,
            ScheduleConstants.API_REPEATING_DAILY,
            ScheduleConstants.API_REPEATING_WEEKLY,
            -> ScheduleConstants.FILTER_REPEATING

            ScheduleConstants.API_ANY_TIME -> ScheduleConstants.FILTER_ANY_TIME
            else -> ScheduleConstants.FILTER_ONE_TIME
        }
    }

    fun handleScheduleFilterItems(copilotListItems: List<CopilotListItem>): CopilotFilterItem {
        val scheduleCountMap = linkedMapOf(
            ScheduleConstants.FILTER_ALL to copilotListItems.size,
            ScheduleConstants.FILTER_ANY_TIME to 0,
            ScheduleConstants.FILTER_REPEATING to 0,
            ScheduleConstants.FILTER_ONE_TIME to 0
        )

        copilotListItems.forEach { copilot ->
            val scheduleDisplayValue = mapScheduleToFilter(copilot.schedule)

            scheduleCountMap[scheduleDisplayValue] =
                scheduleCountMap.getOrDefault(scheduleDisplayValue, 0) + 1

        }

        val filterItems = scheduleCountMap.map { (key, value) ->
            FilterItem(
                filterType = FilterType.SCHEDULE,
                key,
                value,
                key == ScheduleConstants.FILTER_ALL
            )
        }

        return CopilotFilterItem(COPILOT_SCHEDULE_FILTER_TITLE, filterItems)
    }

    fun applyFilter(
        allCopilotItems: List<CopilotListItem>,
        selectedItem: FilterItem
    ): List<CopilotListItem> {

        return when (selectedItem.title) {
            ScheduleConstants.FILTER_REPEATING -> allCopilotItems.filter { copilot ->
                copilot.schedule in listOf(
                    ScheduleConstants.API_REPEATING_YEARLY,
                    ScheduleConstants.API_REPEATING_DAILY,
                    ScheduleConstants.API_REPEATING_WEEKLY,
                )
            }

            ScheduleConstants.FILTER_ANY_TIME -> allCopilotItems.filter {
                it.schedule == ScheduleConstants.API_ANY_TIME
            }

            ScheduleConstants.FILTER_ONE_TIME -> allCopilotItems.filter {
                it.schedule == ""
            }

            else -> allCopilotItems
        }
    }
}