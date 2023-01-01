package com.example.depressionapp.android.note_list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoteListScreen(
    navController: NavController,
    viewModel: NoteListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.loadNotes()
    }

    Scaffold() { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                SearchProjectName(
                    text = state.searchText,
                    isSearchActive = state.isSearchActive,
                    onTextChange = viewModel::onSearchTextChange,
                    onSearchClick = viewModel::onToggleSearch,
                    onCloseClick = viewModel::onToggleSearch,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp)
                )
                this@Column.AnimatedVisibility(
                    visible = !state.isSearchActive,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = "Geo Survey Note",
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp
                    )
                }
                Box(
                    modifier = Modifier.align(Alignment.Center)
                        .fillMaxWidth()
                        .padding(16.dp)
                        .padding(end = 20.dp)

                ) {

                    IconButton(onClick = {
                        navController.navigate("note_detail/-1L")
                    } ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add New Note"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(
                    items = state.reportNotes,
                    key = { it.id!! }
                ) { note ->
                    NoteItem(
                        reportNote = note,
                       onNoteClick = {
                            navController.navigate("note_detail/${note.id}")
                        },
                        onDeleteClick = {
                            viewModel.deleteNoteById(note.id!!)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .animateItemPlacement()
                    )
                }
            }
        }
    }
}



