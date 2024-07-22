package com.example.jetpackcompostroomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class todo(
    @PrimaryKey(autoGenerate = true) var id: Int,
    var title: String
    ,var createdAt: Date
)
