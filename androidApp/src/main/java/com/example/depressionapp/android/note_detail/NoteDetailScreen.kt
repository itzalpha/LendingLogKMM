package com.example.depressionapp.android.note_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.depressionapp.android.R

@Composable
fun NoteDetailScreen(
    noteId: Long,
    navController: NavController,
    viewModel: NoteDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val hasNoteBeenSaved by viewModel.hasNoteBeenSaved.collectAsState()

    LaunchedEffect(key1 = hasNoteBeenSaved) {
        if(hasNoteBeenSaved) {
            navController.popBackStack()
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
            onClick = viewModel::saveNote,
                backgroundColor = Color.Black
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id =
                    R.drawable.ic_baseline_save_24
                    ),
                    contentDescription = "Save note",
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(padding)
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Project Name ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.noteTitle,
                onValueChanged = viewModel::onNoteTitleChanged,
                onFocusChanged = {
                    viewModel.onNoteTitleFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Aims & Objectives & Methodology ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.noteAimsAndObjectives,
                onValueChanged = viewModel::onNoteAimsAndObjectivesChanged,
                onFocusChanged = {
                    viewModel.onNoteAimsAndObjectivesFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Introduction & General Geology ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.noteGeographicalHistory,
                onValueChanged = viewModel::onNoteGeographicalHistoryChanged,
                onFocusChanged = {
                    viewModel.onNoteGeographicalHistoryFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Geology Of The Study Area ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.noteOutcrop,
                onValueChanged = viewModel::onNoteOutcropChanged,
                onFocusChanged = {
                    viewModel.onNoteOutcropFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Conclusion",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.noteConclusion,
             onValueChanged = viewModel::onNoteConclusionChanged,
                onFocusChanged = {
                    viewModel.onNoteConclusionFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

