package com.example.depressionapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.depressionapp.android.note_detail.NoteDetailScreen
import com.example.depressionapp.android.note_list.NoteListScreen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "log_list") {
                    composable(route = "log_list") {
                        NoteListScreen(navController = navController)
                    }
                    composable(
                        route = "log_detail/{logId}",
                        arguments = listOf(
                            navArgument(name = "logId") {
                                type = NavType.LongType
                                defaultValue = -1L
                            }
                        )
                    ) { backStackEntry ->
                        val noteId = backStackEntry.arguments?.getLong("logId") ?: -1L
                       NoteDetailScreen(noteId = noteId, navController = navController)
                    }
                }

        }
    }
}

