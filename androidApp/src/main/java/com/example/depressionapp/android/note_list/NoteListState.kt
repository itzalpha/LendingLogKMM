package com.example.depressionapp.android.note_list

import com.example.depressionapp.domain.note.ReportNote

data class NoteListState(
    val reportNotes: List<ReportNote> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)


