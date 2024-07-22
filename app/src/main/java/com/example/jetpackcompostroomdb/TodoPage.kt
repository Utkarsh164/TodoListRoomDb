package com.example.jetpackcompostroomdb

import android.content.res.Resources.Theme
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoPage(viewModel: StateTodo)
{
    val todolist by viewModel.todolist.observeAsState()
    var content by remember {
        mutableStateOf("")
    }


    Column(modifier = Modifier
        .padding(10.dp, 30.dp, 10.dp, 0.dp)
        .fillMaxSize()) {
        Row (modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically){

            Spacer(modifier = Modifier.size(20.dp))
            OutlinedTextField(value = content, onValueChange = {
                content=it
            }, label = {
                Text(text = "ToDo", fontSize = 15.sp)
            })
            Spacer(modifier = Modifier.size(5.dp))
            Button(onClick = {
                if(!content.isEmpty())
                {viewModel.addTodo(content)
                content=""}
            }) {
                Text(text = "ADD")
            }
        }

        todolist?.let {
            LazyColumn {
                items(todolist!!)
                {
                    todoitem(item = it){
                        viewModel.delTodo(it)
                    }
                }
            }
        }?: Column (modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "Empty", fontSize = 25.sp)
        }
    }
}

@Composable
fun todoitem(item:todo,ondel: (Int) -> Unit)
{
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.primary)
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(modifier = Modifier
            .fillMaxWidth(0.9f)) {
           Text(text =SimpleDateFormat("HH:mm:aa, dd/mm",Locale.ENGLISH).format(item.createdAt)
               ,fontSize = 12.sp,
                color = Color.LightGray)

            Text(text = item.title,
                fontSize = 20.sp,
                color = Color.White)

        }
       IconButton(onClick = {
           ondel(item.id)
       }) {
           Icon(painter = painterResource(id = R.drawable.baseline_delete_24),
               contentDescription = "Delete",
               tint =Color.White)
       }
    }

}