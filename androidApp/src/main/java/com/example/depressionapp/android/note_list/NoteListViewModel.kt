package com.example.depressionapp.android.note_list

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.depressionapp.domain.note.NoteDataSource
import com.example.depressionapp.domain.note.ReportNote
import com.example.depressionapp.domain.note.SearchNotes

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val searchNotes = SearchNotes()
    private val notes = savedStateHandle.getStateFlow("notes", emptyList<ReportNote>())
    private val searchText = savedStateHandle.getStateFlow("searchText", "")
    private val isSearchActive = savedStateHandle.getStateFlow("isSearchActive", false)

    val state = combine(notes, searchText, isSearchActive) { notes, searchText, isSearchActive ->
        NoteListState(
            reportNotes = searchNotes.execute(notes, searchText),
            searchText = searchText,
            isSearchActive = isSearchActive
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteListState())

    fun loadNotes() {
        viewModelScope.launch {
            savedStateHandle["notes"] = noteDataSource.getAllNotes()
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
            noteDataSource.deleteNoteById(id)
            loadNotes()
        }
    }
}
