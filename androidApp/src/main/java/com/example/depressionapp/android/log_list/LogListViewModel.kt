package com.example.depressionapp.android.log_list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depressionapp.domain.log.LogDataSource
import com.example.depressionapp.domain.log.Logs
import com.example.depressionapp.domain.log.SearchLog

@HiltViewModel
class LogListViewModel @Inject constructor(
    private val logDataSource: LogDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val searchLog = SearchLog()
    private val notes = savedStateHandle.getStateFlow("logs", emptyList<Logs>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        LogListState(
            logs = searchLog.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LogListState())

    fun loadLogs() {
        viewModelScope.launch {
            savedStateHandle["logs"] = logDataSource.getAllLogs()
        }
    }

    fun onSearchTextChange(text: String) {
        savedStateHandle["searchText"] = text
    }

    fun onToggleSearch() {
        savedStateHandle["isSearchActive"] = !isSearchActive.value
        if(!isSearchActive.value) {
            savedStateHandle["searchText"] = ""
        }
    }

    fun deleteNoteById(id: Long) {
        viewModelScope.launch {
            logDataSource.deleteLogById(id)
            loadLogs()
        }
    }
}
