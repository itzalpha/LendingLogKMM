package com.example.depressionapp.data.note

import com.example.depressionapp.database.NoteDatabase
import com.example.depressionapp.domain.note.ReportNote
import com.example.depressionapp.domain.note.NoteDataSource
import com.example.depressionapp.domain.time.DateTimeUtil

class SqlDelightNoteDataSource(db: NoteDatabase): NoteDataSource {

    private val queries = db.noteQueries

    override suspend fun insertNote(reportNote: ReportNote) {
        queries.insertNote(
            id = reportNote.id,
            title = reportNote.title,
            aimsAndObjectives = reportNote.aimsAndObjectives,
            outcrop = reportNote.outcrop,
            geographicalHistory = reportNote.geographicalHistory,
            conclusion= reportNote.conclusion,
            created = DateTimeUtil.toEpochMillis(reportNote.created)
        )
    }

    override suspend fun getNoteById(id: Long): ReportNote? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun getAllNotes(): List<ReportNote> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}