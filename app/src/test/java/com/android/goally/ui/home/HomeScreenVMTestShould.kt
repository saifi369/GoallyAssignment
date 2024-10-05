package com.android.goally.ui.home

import com.android.goally.data.model.api.response.copilot.CopilotSuccessResponse
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.data.model.api.response.copilot.ScheduleV2X
import com.android.goally.data.repo.CopilotRepo
import com.android.goally.data.util.ResultHandler
import com.android.goally.ui.home.model.CopilotListItem
import com.android.goally.ui.home.model.FilterItem
import com.android.goally.ui.home.model.FilterType
import com.android.goally.ui.utils.ScheduleMapper
import com.android.goally.utils.getOrAwaitValue
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(InstantTaskExecutionExtension::class, CoroutineTestExtension::class)
class HomeScreenVMTestShould {

    @RelaxedMockK
    private lateinit var copilotRepo: CopilotRepo

    @RelaxedMockK
    private lateinit var scheduleMapper: ScheduleMapper

    @InjectMockKs
    private lateinit var homeScreenVM: HomeScreenVM

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `update coPilotsList when getCopilots is successful`() = runTest {
        val routineList =
            listOf(
                generateTestRoutine("Routine 1"),
                generateTestRoutine("Routine 2")
            )

        coEvery { copilotRepo.getCopilots() } returns
                ResultHandler.Success(CopilotSuccessResponse(null, routineList))
        every { scheduleMapper.mapApiResponseToUiValues(any()) } returns "Display Schedule"
        every { scheduleMapper.handleScheduleFilterItems(any()) } returns mockk(relaxed = true)

        homeScreenVM.getCopilots()
        advanceUntilIdle()

        val result: List<CopilotListItem> = homeScreenVM.coPilotsList.getOrAwaitValue()

        assertEquals(2, result.size)
        assertEquals("Routine 1", result[0].name)
        assertEquals("Routine 2", result[1].name)

        coVerify { copilotRepo.getCopilots() }
    }

    @Test
    fun `should return emptyList when getCopilots return error`() = runTest {
        val message = "Some error"
        val response = ResultHandler.Error(message)
        coEvery { copilotRepo.getCopilots() } returns response

        homeScreenVM.getCopilots()
        advanceUntilIdle()

        assertEquals(0, homeScreenVM.coPilotsList.getOrAwaitValue().size)
        coVerify { copilotRepo.getCopilots() }
    }

    @Test
    fun `should update showFilterTag when folder filter is applied`() {
        val folderFilter = FilterItem(FilterType.FOLDER, "Folder", 1)

        homeScreenVM.applyFilter(folderFilter)

        val filterTag = homeScreenVM.showFilterTag.getOrAwaitValue()

        assertEquals(FilterType.FOLDER, filterTag.first)
        assertTrue(filterTag.second)
    }

    @Test
    fun `should update showFilterTag when schedule filter is applied`() {
        val scheduleFilter = FilterItem(FilterType.SCHEDULE, "Schedule", 1)

        homeScreenVM.applyFilter(scheduleFilter)

        val filterTag = homeScreenVM.showFilterTag.getOrAwaitValue()

        assertEquals(FilterType.SCHEDULE, filterTag.first)
        assertTrue(filterTag.second)
    }

    @Test
    fun `should apply folder filter correctly`() = runTest {
        val routineList = listOf(
            generateTestRoutine(folderName = "Folder 1"),
            generateTestRoutine(folderName = "Folder 2")
        )

        coEvery { copilotRepo.getCopilots() } returns
                ResultHandler.Success(CopilotSuccessResponse(null, routineList))

        homeScreenVM.getCopilots()
        advanceUntilIdle()

        val filterItem = FilterItem(FilterType.FOLDER, "Folder 1", 1)

        homeScreenVM.applyFilter(filterItem)

        val result = homeScreenVM.coPilotsList.getOrAwaitValue()

        assertEquals(1, result.size)
        assertEquals("Routine Name", result[0].name)
        assertEquals("Folder 1", result[0].folderName)
    }

    @Test
    fun `should reset folder filter when applying schedule filter`() {
        val folderFilterItem = FilterItem(FilterType.FOLDER, "Folder1", 1)
        homeScreenVM.applyFilter(folderFilterItem)

        val scheduleFilterItem = FilterItem(FilterType.SCHEDULE, "Schedule1", 1)
        every { scheduleMapper.applyFilter(any(), any()) } returns listOf()

        homeScreenVM.applyFilter(scheduleFilterItem)

        assertTrue(homeScreenVM.coPilotsList.getOrAwaitValue().isEmpty())
        assertEquals(FilterType.SCHEDULE, homeScreenVM.showFilterTag.getOrAwaitValue().first)
    }

    private fun generateTestRoutine(
        routineName: String = "Routine Name",
        folderName: String = "Education",
    ): Routine {
        return Routine(
            activities = emptyList(),
            allowClientToCancel = null,
            allowEarlyStart = null,
            allowRecap = null,
            allowSnooze = null,
            allowToOverride = null,
            allowUpdateNightLightAndNoiseMachine = null,
            appRewards = null,
            audioEvents = null,
            category = null,
            clientId = null,
            createdAt = null,
            createdBy = null,
            ctaOrdering = null,
            devices = null,
            earlyStartMinutes = null,
            enableChillZoneWatch = null,
            enableEmotionalFeedback = null,
            enableSpeedMonitoring = null,
            enableVacationMode = null,
            enableWeatherWatch = null,
            entityType = null,
            folder = folderName,
            folderId = null,
            id = null,
            imgURL = "routine.png",
            isCreatedByDevice = null,
            isMarkedHot = null,
            isScheduledByV2 = null,
            isVisibleToAudience = null,
            label = null,
            lastSchedule = null,
            libraryType = null,
            limitEarlyStart = null,
            limitRunPerDay = null,
            limitRunPerHour = null,
            migrated = null,
            name = routineName,
            narration = null,
            notifsV2SoundName = null,
            notifsV2SoundUrl = null,
            numberOfPointsLate = null,
            numberOfPointsOnTime = null,
            numberOfPuzzlesLate = null,
            numberOfPuzzlesOnTime = null,
            numberOfRunPerDay = null,
            numberOfRunPerHour = null,
            numberOfSnoozeAllowed = null,
            openAIMp3Text = null,
            openAIMp3Url = null,
            ordering = null,
            parentRoutineId = null,
            requiredRewardApproval = null,
            rewardType = null,
            routineFolder = null,
            routineNotifications = null,
            routineNotificationsV2 = null,
            schedule = null,
            scheduleV2 = ScheduleV2X(
                dailyRepeatValues = null,
                timeType = "daily",
                timeValue = null,
                type = "fixed",
                yearlyRepeatDateValue = null
            ),
            showOnLearnerApp = null,
            showTimer = null,
            tags = null,
            templateDisabledClientIds = null,
            type = null,
            updatedAt = null,
            v = null
        )
    }
}
