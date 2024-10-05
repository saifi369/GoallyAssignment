package com.android.goally.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.goally.data.model.api.response.copilot.Routine
import com.android.goally.data.repo.CopilotRepo
import com.android.goally.data.util.ResultHandler
import com.android.goally.ui.home.model.CopilotFilterItem
import com.android.goally.ui.home.model.CopilotListItem
import com.android.goally.ui.home.model.FilterItem
import com.android.goally.ui.home.model.FilterType
import com.android.goally.ui.utils.COPILOT_FOLDER_FILTER_TITLE
import com.android.goally.ui.utils.ScheduleConstants
import com.android.goally.ui.utils.ScheduleMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenVM @Inject constructor(
    private val copilotRepo: CopilotRepo,
    private val scheduleMapper: ScheduleMapper
) : ViewModel() {

    private val allCopilotItems = mutableListOf<CopilotListItem>()

    private val _coPilotsList = MutableLiveData<List<CopilotListItem>>()
    val coPilotsList: LiveData<List<CopilotListItem>> = _coPilotsList

    private val _showFilterTag = MutableLiveData<Pair<FilterType, Boolean>>()
    val showFilterTag: LiveData<Pair<FilterType, Boolean>> = _showFilterTag

    private var copilotFolderFilterItem: CopilotFilterItem? = null
    private var copilotScheduleFilterItem: CopilotFilterItem? = null

    init {
        getCopilots()
    }

    fun getCopilots() {
        viewModelScope.launch {
            when (val response = copilotRepo.getCopilots()) {
                is ResultHandler.Success -> {
                    response.data.routines?.let {
                        handleCopilotListResponse(it)
                    }
                }

                is ResultHandler.Error -> {
                    _coPilotsList.value = emptyList()
                    //skipping this because this isn't part of the assignment
                    //do something in case of error, update UI or something
                }
            }
        }
    }

    private fun handleCopilotListResponse(routine: List<Routine>) {
        val copilotItemList = routine
            .sortedBy { it.name }
            .map {
                CopilotListItem(
                    name = it.name ?: "",
                    imageUrl = it.imgURL ?: "",
                    schedule = it.scheduleV2?.type ?: "",
                    displaySchedule = scheduleMapper.mapApiResponseToUiValues(it.scheduleV2),
                    folderName = it.folder ?: "",
                    activities = it.activities ?: emptyList()
                )
            }
        _coPilotsList.value = copilotItemList
        updateCopilotList(copilotItemList)
    }

    private fun updateCopilotList(copilotItemList: List<CopilotListItem>) {
        allCopilotItems.clear()
        allCopilotItems.addAll(copilotItemList)

        handleFolderFilterItems(copilotItemList)
        handleScheduleFilterItems(copilotItemList)
    }

    private fun handleFolderFilterItems(copilotItemList: List<CopilotListItem>) {
        val folderCountMap = copilotItemList
            .groupingBy { it.folderName }
            .eachCount()
            .toSortedMap()

        val othersCount = folderCountMap["Others"]
        val sortedFolderItems = folderCountMap
            .filterKeys { it != "Others" }
            .map { FilterItem(filterType = FilterType.FOLDER, it.key, it.value) }
            .toMutableList()

        othersCount?.let {
            sortedFolderItems.add(
                FilterItem(
                    filterType = FilterType.FOLDER,
                    "Others",
                    it
                )
            )
        }
        val folderItems =
            listOf(
                FilterItem(
                    filterType = FilterType.FOLDER,
                    "All",
                    copilotItemList.size,
                    isSelected = true
                )
            ) + sortedFolderItems

        copilotFolderFilterItem =
            CopilotFilterItem(COPILOT_FOLDER_FILTER_TITLE, folderItems)
    }

    private fun handleScheduleFilterItems(copilotListItems: List<CopilotListItem>) {
        copilotScheduleFilterItem = scheduleMapper.handleScheduleFilterItems(copilotListItems)
    }

    fun getCopilotFolderFilterItems() = copilotFolderFilterItem
    fun getCopilotScheduleFilterItems() = copilotScheduleFilterItem

    fun applyFilter(filterItem: FilterItem) {
        if (filterItem.isSelected)
            return

        when (filterItem.filterType) {
            FilterType.FOLDER -> {
                resetFilter(copilotScheduleFilterItem)
                applyFolderListFilter(filterItem)
            }

            FilterType.SCHEDULE -> {
                resetFilter(copilotFolderFilterItem)
                applyScheduleListFilter(filterItem)
            }
        }
    }

    private fun resetFilter(copilotFilterItem: CopilotFilterItem?) {
        copilotFilterItem?.items?.find { it.isSelected }?.isSelected = false
        copilotFilterItem?.items?.find { it.title == ScheduleConstants.FILTER_ALL }?.isSelected =
            true
    }

    private fun applyFolderListFilter(selectedItem: FilterItem) {
        copilotFolderFilterItem?.items?.find { it.isSelected }?.isSelected = false
        selectedItem.isSelected = true

        if (selectedItem.title == "All") {
            _coPilotsList.value = allCopilotItems
            _showFilterTag.value = FilterType.FOLDER to false
            return
        }

        _coPilotsList.value = allCopilotItems.filter { copilot ->
            copilot.folderName.equals(
                selectedItem.title,
                ignoreCase = true
            )
        }
        _showFilterTag.value = FilterType.FOLDER to true
    }

    private fun applyScheduleListFilter(selectedItem: FilterItem) {
        copilotScheduleFilterItem?.items?.find { it.isSelected }?.isSelected = false
        selectedItem.isSelected = true

        if (selectedItem.title == ScheduleConstants.FILTER_ALL) {
            _coPilotsList.value = allCopilotItems
            _showFilterTag.value = FilterType.SCHEDULE to false
            return
        }

        _coPilotsList.value = scheduleMapper.applyFilter(allCopilotItems, selectedItem)
        _showFilterTag.value = FilterType.SCHEDULE to true
    }
}