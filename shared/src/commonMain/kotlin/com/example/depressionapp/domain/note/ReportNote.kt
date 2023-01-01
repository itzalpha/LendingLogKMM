package com.example.depressionapp.domain.note

import kotlinx.datetime.LocalDateTime

data class ReportNote(
    val id: Long?,
    val title: String,
    val aimsAndObjectives : String,
    val outcrop: String,
    val geographicalHistory : String,
    val conclusion : String,
    val created : LocalDateTime
)
{
}