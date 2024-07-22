package com.example.jetpackcompostroomdb.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.jetpackcompostroomdb.todo

@Database(entities = [todo::class], version = 1)
@TypeConverters(convertors::class)
abstract class TodoDatabase : RoomDatabase() {

    companion object {
        const val name = "Todo_db"
    }

    abstract fun getTodoDao() : TodoDao
}
