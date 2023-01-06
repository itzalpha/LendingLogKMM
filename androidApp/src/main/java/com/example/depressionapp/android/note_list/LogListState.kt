package com.example.depressionapp.android.note_list

import com.example.depressionapp.domain.log.Logs

data class LogListState(
    val logs: List<Logs> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)


