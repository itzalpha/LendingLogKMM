package com.example.depressionapp.android.note_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depressionapp.domain.note.ReportNote
import com.example.depressionapp.domain.note.NoteDataSource
import com.example.depressionapp.domain.time.DateTimeUtil

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val noteTitle = savedStateHandle.getStateFlow("noteTitle", "")
    private val noteAimsAndObjectives = savedStateHandle.getStateFlow("noteAimsAndObjectives", "")
    private val noteOutcrop = savedStateHandle.getStateFlow("noteOutcrop", "")
    private val noteGeographicalHistory = savedStateHandle.getStateFlow("noteGeographicalHistory", "")
    private val noteConclusion = savedStateHandle.getStateFlow("noteConclusion", "")

    val state = combine(noteTitle , noteAimsAndObjectives , noteGeographicalHistory , noteOutcrop , noteConclusion  ){
        title, aimsAndObjectives , geographicalHistory  , outCrops , conclusion ->
        NoteDetailState(
            noteTitle = title ,
            noteAimsAndObjectives = aimsAndObjectives,
            noteGeographicalHistory = geographicalHistory,
            noteOutcrop = outCrops ,
            noteConclusion = conclusion,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private val _hasNoteBeenSaved = MutableStateFlow(false)
    val hasNoteBeenSaved = _hasNoteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let { existingNoteId ->
            if(existingNoteId == -1L) {
                return@let
            }
            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                noteDataSource.getNoteById(existingNoteId)?.let { note ->
                    savedStateHandle["noteTitle"] = note.title
                    savedStateHandle["noteAimsAndObjectives"] = note.aimsAndObjectives
                    savedStateHandle["noteOutcrop"] = note.outcrop
                    savedStateHandle["noteGeographicalHistory"] = note.geographicalHistory
                    savedStateHandle["noteConclusion"] = note.conclusion


                }
            }
        }
    }

    fun onNoteTitleChanged(text: String) {
        savedStateHandle["noteTitle"] = text
    }

    fun onNoteAimsAndObjectivesChanged(text: String) {
        savedStateHandle["noteAimsAndObjectives"] = text
    }

    fun onNoteOutcropChanged(text: String) {
        savedStateHandle["noteOutcrop"] = text
    }

    fun onNoteGeographicalHistoryChanged(text: String) {
        savedStateHandle["noteGeographicalHistory"] = text
    }

    fun onNoteConclusionChanged(text: String) {
        savedStateHandle["noteConclusion"] = text
    }


    fun onNoteTitleFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteTitleFocused"] = isFocused
    }
    fun onNoteAimsAndObjectivesFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteAimsAndObjectivesFocused"] = isFocused
    }

    fun onNoteOutcropFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteOutcropFocused"] = isFocused
    }
    fun onNoteGeographicalHistoryFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteGeographicalHistoryFocused"] = isFocused
    }

    fun onNoteConclusionFocusChanged(isFocused: Boolean) {
        savedStateHandle["isNoteConclusionFocused"] = isFocused
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                ReportNote(
                    id = existingNoteId,
                    title = noteTitle.value,
                    aimsAndObjectives = noteAimsAndObjectives.value,
                    outcrop = noteOutcrop.value,
                    geographicalHistory = noteGeographicalHistory.value ,
                    conclusion = noteConclusion.value,
                    created = DateTimeUtil.now()
                )
            )
            _hasNoteBeenSaved.value = true
        }
    }
}

