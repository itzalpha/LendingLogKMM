package com.example.depressionapp.android.note_list

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.depressionapp.domain.note.ReportNote
import com.example.depressionapp.domain.time.DateTimeUtil

@Composable
fun NoteItem(
    context: Context = LocalContext.current,
    reportNote: ReportNote,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(reportNote.created) {
        DateTimeUtil.formatNoteDate(reportNote.created)
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.DarkGray , RectangleShape)
            .clickable { onNoteClick() }
            .padding(16.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
           modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = formattedDate,
            )
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete note",
                modifier = Modifier
                    .clickable(MutableInteractionSource(), null) {
                        val builder = AlertDialog.Builder(context)
                        builder.setMessage("Are You Sure You Want To Delete This Report Note")
                            .setCancelable(false)
                            .setPositiveButton("Yes") { _, _ ->
                                onDeleteClick()
                            }
                            .setNegativeButton("No") {dialog ,_ ->
                                  dialog.cancel()
                            }
                        val alerts = builder.create()
                        alerts.show()

                    }
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "Project Name : ${reportNote.title}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )

    }

}


