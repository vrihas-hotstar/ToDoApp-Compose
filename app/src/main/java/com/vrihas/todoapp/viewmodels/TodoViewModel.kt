package com.vrihas.todoapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vrihas.todoapp.data.Todo
import com.vrihas.todoapp.data.TodoDataRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoDataRepositoryImpl: TodoDataRepositoryImpl
) : ViewModel() {

    val todoList: LiveData<List<Todo>> = todoDataRepositoryImpl.getAllTodos()

     fun addTodo(todo: Todo) {
       viewModelScope.launch {
           todoDataRepositoryImpl.addTodo(todo)
       }

    }

    fun updateTodo(todo: Todo) {
        viewModelScope.launch{
            todoDataRepositoryImpl.updateTodo(todo)
        }
    }

    fun deleteTodo(todo: Todo) {
        viewModelScope.launch {
            todoDataRepositoryImpl.deleteTodo(todo)
        }
    }

    fun getAllTodos(): LiveData<List<Todo>> {
        return todoList
    }

}