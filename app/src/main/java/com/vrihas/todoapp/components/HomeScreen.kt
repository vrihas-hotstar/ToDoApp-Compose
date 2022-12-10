package com.vrihas.todoapp.components

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vrihas.todoapp.viewmodels.TodoViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel
) {
    val openDialog = remember { mutableStateOf(false) }
    val todoList = viewModel.getAllTodos().observeAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openDialog.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "TODO item Add"
                )
                if (openDialog.value) {
                    FullScreenDialog(
                        openDialog = openDialog,
                        onClickAdd = { newTodo ->
                            viewModel.addTodo(newTodo)
                        }
                    )
                }
            }
        },
        topBar = {
            TopAppBar {
                Text(text = "ToDo App")
            }
        }
    ) {
        if (todoList.value?.size == 0) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp, top = 16.dp),
                text = "Add your Todos",
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                todoList.value?.size?.let {
                    items(it) { index ->
                        todoList.value?.get(index)?.let { todo ->
                            TodoCard(
                                modifier = modifier.padding(8.dp),
                                todo = todo,
                                onCLickTodo = {
                                    // todo: open a alert box to edit
                                },
                                onDeleteTodo = {
                                    viewModel.deleteTodo(todo)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}