package com.vrihas.todoapp.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.vrihas.todoapp.data.Todo

@Composable
fun FullScreenDialog(
    openDialog: MutableState<Boolean>,
    isNewTodo: Boolean,
    todo: Todo,
    onClickAdd: (todo: Todo) -> Unit = {},
    onClickEdit: (todo: Todo) -> Unit = {}
) {
    var todoTitle by remember { mutableStateOf(TextFieldValue(todo.title)) }
    var todoNote by remember { mutableStateOf(TextFieldValue(todo.note)) }

    AlertDialog(
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
        shape = RoundedCornerShape(12.dp),
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            openDialog.value = false
        },
        title = {
            val heading = if(isNewTodo) "Add Todo" else "Update Todo"
            Text(
                text = heading, modifier = Modifier.padding(20.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        },
        text = {
            Column(
                modifier = Modifier.padding(10.dp),
            ) {
                TextField(
                    value = todoTitle,
                    onValueChange = { newTitle ->
                        todoTitle = newTitle
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
                TextField(
                    value = todoNote,
                    onValueChange = { newNote ->
                        todoNote = newNote
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(top = 20.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    )
                )
            }
        },
        confirmButton = {
            if (isNewTodo) {
                Button(
                    onClick = {
                        if (todoTitle.text.isNotEmpty() && todoNote.text.isNotEmpty()) {
                            onClickAdd(
                                Todo(todo.id, title = todoTitle.text, note = todoNote.text)
                            )
                            openDialog.value = false // for closing the dialog
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                ) {
                    Text(
                        "Add", color = Color.White,
                    )
                }
            } else {
                Button(
                    onClick = {
                        if (todoTitle.text.isNotEmpty() && todoNote.text.isNotEmpty()) {
                            onClickEdit(
                                Todo(todo.id, title = todoTitle.text, note = todoNote.text)
                            )
                            openDialog.value = false // for closing the dialog
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green),
                ) {
                    Text(
                        "Update", color = Color.White,
                    )
                }
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    openDialog.value = false //close the dialog to assign the value false when Cancel button is clicked
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            ) {
                Text(
                    "Cancel", color = Color.White,
                )
            }
        }
    )
}