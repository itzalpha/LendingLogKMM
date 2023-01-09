package com.example.depressionapp.android.log_list

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
import com.example.depressionapp.domain.log.Logs
import com.example.depressionapp.domain.time.DateTimeUtil

@Composable
fun NoteItem(
    context: Context = LocalContext.current,
    logs: Logs,
    onNoteClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val formattedDate = remember(logs.created) {
        DateTimeUtil.formatNoteDate(logs.created)
    }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray , RectangleShape)
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
                contentDescription = "Delete Log",
                modifier = Modifier
                    .clickable(MutableInteractionSource(), null) {
                        val builder = AlertDialog.Builder(context)
                        builder.setMessage("Are You Sure You Want To Delete This Log\nHas the borrowed Item been returned")
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
            text = "Object Lent : ${logs.objectLent}",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(4.dp))

    }

}


