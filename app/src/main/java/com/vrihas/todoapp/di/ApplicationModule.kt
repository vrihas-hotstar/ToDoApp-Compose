package com.vrihas.todoapp.di

import android.content.Context
import androidx.room.Room
import com.vrihas.todoapp.data.TodoDataRepositoryImpl
import com.vrihas.todoapp.room.ToDoDatabase
import com.vrihas.todoapp.room.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Provides
    fun provideTodoDao(toDoDatabase: ToDoDatabase): TodoDao {
        return toDoDatabase.todoDao()
    }

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "todo_database"
        ).build()
    }

    @Provides
    fun provideTodoDataRepositoryImpl(todoDao: TodoDao) = TodoDataRepositoryImpl(todoDao)
}