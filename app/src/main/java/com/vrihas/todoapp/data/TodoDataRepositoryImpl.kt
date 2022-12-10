package com.vrihas.todoapp.data

import androidx.lifecycle.LiveData
import com.vrihas.todoapp.room.TodoDao
import javax.inject.Inject

class TodoDataRepositoryImpl @Inject constructor(private val todoDao: TodoDao){

    fun getAllTodos(): LiveData<List<Todo>> {
        return todoDao.getAllNotes()
    }

    suspend fun addTodo(todo: Todo) {
        todoDao.insertTodo(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        todoDao.updateTodo(todo)
    }

    suspend fun deleteTodo(todo: Todo) {
        todoDao.deleteTodo(todo)
    }
}