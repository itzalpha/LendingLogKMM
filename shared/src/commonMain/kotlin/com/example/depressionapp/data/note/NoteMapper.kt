package com.example.depressionapp.data.note

import com.example.depressionapp.domain.note.ReportNote
import database.NoteEntity
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun NoteEntity.toNote(): ReportNote {
    return ReportNote(
        id = id,
        title = title,
        aimsAndObjectives = aimsAndObjectives,
        outcrop = outcrop,
        geographicalHistory = geographicalHistory,
        conclusion = conclusion,
        created = Instant
            .fromEpochMilliseconds(created)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}