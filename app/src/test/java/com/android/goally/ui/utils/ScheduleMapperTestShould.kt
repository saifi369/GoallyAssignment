package com.android.goally.ui.utils

import com.android.goally.data.model.api.response.copilot.DailyRepeatValues
import com.android.goally.data.model.api.response.copilot.ScheduleV2X
import com.android.goally.ui.home.model.CopilotListItem
import com.android.goally.ui.home.model.FilterItem
import com.android.goally.ui.home.model.FilterType
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

class ScheduleMapperTestShould {

    private val scheduleMapper = ScheduleMapper()

    @ParameterizedTest
    @CsvSource(
        "REPEATING_YEARLY, ${ScheduleConstants.FILTER_REPEATING}",
        "REPEATING_DAILY, ${ScheduleConstants.FILTER_REPEATING}",
        "REPEATING_WEEKLY, ${ScheduleConstants.FILTER_REPEATING}",
        "ANY_TIME, ${ScheduleConstants.FILTER_ANY_TIME}",
        "'', ${ScheduleConstants.FILTER_ONE_TIME}"
    )
    fun `return filterValue when schedule is received`(schedule: String, expectedFilter: String) {
        val result = scheduleMapper.mapScheduleToFilter(schedule)

        assertEquals(expectedFilter, result)
    }

    @Test
    fun `return filterItemsList when handleScheduleFilterItems is called with valid copilot list`() {
        val copilotListItems = listOf(
            mockk<CopilotListItem> { every { schedule } returns ScheduleConstants.API_REPEATING_DAILY },
            mockk<CopilotListItem> { every { schedule } returns ScheduleConstants.API_ANY_TIME },
        )

        val expectedFilterItems = listOf(
            FilterItem(FilterType.SCHEDULE, ScheduleConstants.FILTER_ALL, 2, true),
            FilterItem(FilterType.SCHEDULE, ScheduleConstants.FILTER_ANY_TIME, 1, false),
            FilterItem(FilterType.SCHEDULE, ScheduleConstants.FILTER_REPEATING, 1, false),
            FilterItem(FilterType.SCHEDULE, ScheduleConstants.FILTER_ONE_TIME, 0, false)
        )

        val result = scheduleMapper.handleScheduleFilterItems(copilotListItems)

        assertEquals(expectedFilterItems, result.items)
    }

    @Test
    fun `test applyFilter for repeating schedules`() {
        val repeatingCopilotItem =
            mockk<CopilotListItem> { every { schedule } returns ScheduleConstants.API_REPEATING_DAILY }

        val allCopilotItems = listOf(repeatingCopilotItem,
            mockk { every { schedule } returns ScheduleConstants.API_ANY_TIME },
            mockk { every { schedule } returns "" })

        val selectedItem =
            FilterItem(FilterType.SCHEDULE, ScheduleConstants.FILTER_REPEATING, 1, false)
        val expectedFilteredItems = listOf(repeatingCopilotItem)

        val result = scheduleMapper.applyFilter(allCopilotItems, selectedItem)

        assertEquals(expectedFilteredItems, result)
    }

    @Test
    fun `test applyFilter for any time schedule`() {

        val allCopilotItems =
            listOf(mockk<CopilotListItem> { every { schedule } returns ScheduleConstants.API_REPEATING_DAILY },
                mockk { every { schedule } returns ScheduleConstants.API_ANY_TIME },
                mockk { every { schedule } returns "" })

        val selectedItem =
            FilterItem(FilterType.SCHEDULE, ScheduleConstants.FILTER_ALL, 1, false)

        val result = scheduleMapper.applyFilter(allCopilotItems, selectedItem)

        assertEquals(allCopilotItems, result)
    }

    @ParameterizedTest
    @CsvSource(
        "REPEATING_YEARLY, Yearly",
        "REPEATING_DAILY, Not Scheduled",
        "ANY_TIME, Not Scheduled",
        "'', Not Scheduled"
    )
    fun `return expected display value for schedule type`(scheduleType: String, expected: String) {
        val schedule = ScheduleV2X(
            dailyRepeatValues = null,
            type = scheduleType,
            timeValue = null,
            timeType = null,
            yearlyRepeatDateValue = null
        )

        val result = scheduleMapper.mapApiResponseToUiValues(schedule)

        assertEquals(expected, result)
    }

    @ParameterizedTest
    @MethodSource("dailyRepeatValuesProvider")
    fun `return expected display value based on daily repeat values`(
        dailyRepeatValues: DailyRepeatValues,
        expected: String
    ) {
        val schedule = ScheduleV2X(dailyRepeatValues = dailyRepeatValues, null, null, null, null)

        val result = scheduleMapper.mapApiResponseToUiValues(schedule)

        assertEquals(expected, result)
    }

    companion object {
        @JvmStatic
        private fun dailyRepeatValuesProvider() =
            listOf(// Test case with all weekdays and weekends scheduled
                Arguments.of(
                    DailyRepeatValues(
                        listOf("9:00 AM", "10:00 AM"),
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        listOf("10:00 AM"),
                        listOf("10:00 AM")
                    ), ScheduleConstants.DISPLAY_EVERYDAY
                ),

                // Test case with all weekdays scheduled but no weekends
                Arguments.of(
                    DailyRepeatValues(
                        listOf("9:00 AM", "10:00 AM"),
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        null,
                        null
                    ), ScheduleConstants.DISPLAY_WEEKDAYS
                ),

                // Test case with all weekends scheduled but no weekdays
                Arguments.of(
                    DailyRepeatValues(
                        null,
                        null,
                        null,
                        null,
                        null,
                        listOf("10:00 AM"),
                        listOf("10:00 AM")
                    ), ScheduleConstants.DISPLAY_WEEKENDS
                ),

                // Test case with some weekdays scheduled
                Arguments.of(
                    DailyRepeatValues(
                        listOf("9:00 AM"),
                        listOf("9:00 AM"),
                        null,
                        null,
                        listOf("9:00 AM"),
                        listOf("10:00 AM"),
                        null
                    ), "Mon, Tue, Fri, Sat"
                ),

                // Test case with no scheduled days
                Arguments.of(
                    DailyRepeatValues(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    ), ScheduleConstants.DISPLAY_NOT_SCHEDULED
                )
            )
    }
}