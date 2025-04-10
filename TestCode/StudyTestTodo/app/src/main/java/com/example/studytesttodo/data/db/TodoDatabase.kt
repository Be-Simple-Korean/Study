package com.example.studytesttodo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.studytesttodo.domain.Todo

@Database(entities = [TodoEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase(){
    abstract fun todoDao():TodoDAO
}