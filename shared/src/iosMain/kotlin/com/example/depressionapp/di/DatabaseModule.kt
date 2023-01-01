package com.example.depressionapp.di

import com.example.depressionapp.data.note.SqlDelightNoteDataSource
import com.example.depressionapp.database.NoteDatabase
import com.example.depressionapp.domain.note.NoteDataSource
import com.example.depressionapp.local.DatabaseDriverFactory


class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NoteDatabase(factory.createDriver()))
    }
}