package com.vrihas.todoapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vrihas.todoapp.data.Todo

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TodoCard(
    modifier: Modifier = Modifier,
    todo: Todo,
    onCLickTodo: () -> Unit = {},
    onDeleteTodo: () -> Unit = {}
) {
    Card(
        onClick = onCLickTodo,
        modifier = modifier
            .padding(top = 8.dp)
            .shadow(4.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            Column(
                modifier = modifier.width(260.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = todo.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = modifier.padding(top = 2.dp, bottom = 2.dp)
                )
                Text(
                    text = todo.note,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,

                )
            }
            IconButton(
                onClick = onDeleteTodo
            ) {
                Icon(
                    Icons.Rounded.Delete,
                    contentDescription = "delete icon",
                )
            }

        }

    }

}