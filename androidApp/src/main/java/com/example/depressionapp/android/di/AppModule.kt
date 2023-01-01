package com.example.depressionapp.android.di

import android.app.Application
import com.example.depressionapp.data.note.SqlDelightNoteDataSource
import com.example.depressionapp.database.NoteDatabase
import com.example.depressionapp.domain.note.NoteDataSource
import com.example.depressionapp.local.DatabaseDriverFactory
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(driver: SqlDriver): NoteDataSource {
        return SqlDelightNoteDataSource(NoteDatabase(driver))
    }
}