package com.example.jetpackcompostroomdb

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class StateTodo:ViewModel()
{

    val tododao=MainActivity.todoDatabase.getTodoDao()

    val todolist :LiveData<List<todo>> = tododao.getAllTodo()

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title : String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            tododao.addTodo(todo(id=System.currentTimeMillis().toInt(),
                title=title
                , createdAt = Date.from(Instant.now())))
        }

    }

    fun delTodo(id:Int)
    {
        viewModelScope.launch(Dispatchers.IO) {
        tododao.delTodo(id)
        }
    }
}