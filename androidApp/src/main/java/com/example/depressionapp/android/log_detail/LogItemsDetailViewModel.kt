package com.example.depressionapp.android.log_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depressionapp.domain.log.Logs
import com.example.depressionapp.domain.log.LogDataSource
import com.example.depressionapp.domain.time.DateTimeUtil

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogItemsDetailViewModel @Inject constructor(
    private val logDataSource: LogDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val name = savedStateHandle.getStateFlow("name", "")
    private val objectLent = savedStateHandle.getStateFlow("objectLent", "")
    private val time = savedStateHandle.getStateFlow("time", "")

    val state = combine(name , objectLent , time ){
            name, objectLent , time ->
        LogItemDetailState(
            name = name ,
            objectLent = objectLent,
            time = time,

        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), LogItemDetailState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("logId")?.let { existingNoteId ->
            if(existingNoteId == -1L) {
                return@let
            }
            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                logDataSource.getLogById(existingNoteId)?.let { log ->
                    savedStateHandle["name"] = log.name
                    savedStateHandle["objectLent"] = log.objectLent
                    savedStateHandle["time"] = log.time


                }
            }
        }
    }

    fun onNameChanged(text: String) {
        savedStateHandle["name"] = text
    }
    fun onObjectLentChanged(text: String) {
        savedStateHandle["objectLent"] = text
    }
    fun onTimeChanged(text: String) {
        savedStateHandle["time"] = text
    }

    fun onNameFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNameFocused"] = isFocused
    }
    fun onObjectLentFocusChanged(isFocused: Boolean) {
        savedStateHandle["isObjectLentFocused"] = isFocused
    }
    fun onTimeFocusChanged(isFocused: Boolean) {
        savedStateHandle["isObjectLentFocused"] = isFocused
    }


    fun saveNote() {
        viewModelScope.launch {
            logDataSource.insertLog(
                Logs(
                    id = existingNoteId,
                    name = name.value,
                    objectLent = objectLent.value,
                    time = time.value,
                    created = DateTimeUtil.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}

