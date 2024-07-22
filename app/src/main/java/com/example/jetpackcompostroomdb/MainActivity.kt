package com.example.jetpackcompostroomdb

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.jetpackcompostroomdb.db.TodoDatabase
import com.example.jetpackcompostroomdb.ui.theme.JetpackCompostRoomDBTheme

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todoDatabase= Room.databaseBuilder(
            applicationContext,
            TodoDatabase::class.java,
            TodoDatabase.name
        ).build()

        enableEdgeToEdge()
        val viewModel= ViewModelProvider(this)[StateTodo::class.java]
        setContent {

            TodoPage(viewModel)
        }
    }
}

