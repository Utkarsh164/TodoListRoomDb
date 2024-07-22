package com.example.jetpackcompostroomdb.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.jetpackcompostroomdb.todo


@Dao
interface TodoDao
{
    @Query("select * from todo")
    fun getAllTodo() : LiveData<List<todo>>

    @Insert
    fun addTodo(todo :  todo)

    @Query("delete from todo where id= :id")
    fun delTodo(id : Int)
}