package com.vrihas.todoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vrihas.todoapp.data.Todo

@Database(entities = [Todo::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract fun todoDao(): TodoDao
}