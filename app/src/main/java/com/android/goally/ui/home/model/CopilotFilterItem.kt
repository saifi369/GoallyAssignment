package com.android.goally.ui.home.model

data class CopilotFilterItem(
    val title: String,
    var items: List<FilterItem>
)

data class FilterItem(
    val filterType: FilterType,
    val title: String,
    val count: Int,
    var isSelected: Boolean = false
)

enum class FilterType {
    FOLDER,
    SCHEDULE
}