package com.example.depressionapp.domain.note

interface NoteDataSource {
    suspend fun insertNote(reportNote: ReportNote)
    suspend fun getNoteById(id: Long): ReportNote?
    suspend fun getAllNotes(): List<ReportNote>
    suspend fun deleteNoteById(id: Long)
}