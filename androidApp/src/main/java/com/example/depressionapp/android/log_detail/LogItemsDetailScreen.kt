package com.example.depressionapp.android.log_detail

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
    viewModel: LogItemsDetailViewModel = hiltViewModel()
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
                    contentDescription = "Save Log",
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
                text = "Name Of Borrower ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.name,
                onValueChanged = viewModel::onNameChanged,
                onFocusChanged = {
                    viewModel.onNameFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Object Lent ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.objectLent,
                onValueChanged = viewModel::onObjectLentChanged,
                onFocusChanged = {
                    viewModel.onObjectLentFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Borrower Contact",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            TransparentHintTextField(
                text = state.time,
                onValueChanged = viewModel::onTimeChanged,
                onFocusChanged = {
                    viewModel.onTimeFocusChanged(it.isFocused)
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 18.sp)
            )
            Spacer(modifier = Modifier.height(16.dp))


        }
    }
}

